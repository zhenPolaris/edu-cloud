package com.zhen.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.commonutils.JwtUtils;
import com.zhen.commonutils.MD5;
import com.zhen.commonutils.SystemConstant;
import com.zhen.servicebase.exception.SystemException;
import com.zhen.ucenter.entity.UcenterMember;
import com.zhen.ucenter.entity.vo.LoginVo;
import com.zhen.ucenter.entity.vo.RegisterVo;
import com.zhen.ucenter.mapper.UcenterMemberMapper;
import com.zhen.ucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author zhen
 * @since 2023-02-16 08:53:59
 */
@Service("ucenterMemberService")
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
            throw new SystemException(20001, "手机号或密码为空");
        }

        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("mobile", phone);
        //账号校验
        UcenterMember ucenterMember = baseMapper.selectOne(ucenterMemberQueryWrapper);
        if (ucenterMember == null){
            throw new SystemException(20001, "用户未注册");
        }

        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new SystemException(20001, "密码错误");
        }
        //判断用户是否禁用
        if (ucenterMember.getIsDisabled()){
            throw new SystemException(20001,"用户被禁用");
        }

        //生成jwtToken
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());

        return token;

    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取前端传来的数据
        String nickname = registerVo.getNickname(); //昵称
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String password = registerVo.getPassword(); //密码

        //非空判断
        if (StringUtils.isEmpty(nickname)
                ||StringUtils.isEmpty(code)
                ||StringUtils.isEmpty(mobile)
                ||StringUtils.isEmpty(password)){
            throw new SystemException(20001,"传来的数据有空值，注册失败");
        }

        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)){
            throw new SystemException(20001,"验证码错误，请重新输入");
        }

        //手机号不能重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>=1){
            throw new SystemException(20001,"手机号已注册");
        }

        //数据添加到数据库中
        UcenterMember member = new UcenterMember();
        member.setPassword(MD5.encrypt(password));//密码加密
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar(SystemConstant.TEACHER_AVATAR_COMMON_URL);
        baseMapper.insert(member);

    }

    //根据openid 查询用户
    @Override
    public UcenterMember getMemberByOpenId(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Integer getCountRegister(String day) {
        return baseMapper.getCountRegister(day);
    }
}

