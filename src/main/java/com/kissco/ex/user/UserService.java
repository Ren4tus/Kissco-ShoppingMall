package com.kissco.ex.user;

import com.kissco.ex.execption.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    
    private SiteUserDto of(SiteUser siteUser) {
        return this.modelMapper.map(siteUser, SiteUserDto.class);
    }

    public SiteUserDto create(String username, String email, String password, String name, String phone, String detail_address) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);
        user.setDetail_address(detail_address);
        user.setRegDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return of(user);
    }
    
    public SiteUserDto getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return of(siteUser.get());
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }

    public void update(SiteUserDto siteUserDto) {

        userMapper.updateUser(siteUserDto);
    }

    public void delete(SiteUserDto siteUserDto) {

        userMapper.deleteUser(siteUserDto);
    }
}