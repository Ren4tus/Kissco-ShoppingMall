package com.kissco.ex.user;

import com.kissco.ex.domain.OrderItem;
import com.kissco.ex.execption.DataNotFoundException;
import com.kissco.ex.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final ItemRepository itemRepository;

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
        user.setCartCount(0);
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
    public List<OrderItem> getCart(String username)
    {
        	Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        	if(siteUser.isPresent())
        	{
        		return siteUser.get().getCartItems();
        	}
        	else
        	{
        		throw new DataNotFoundException("siteuser not found");
        	}
    }
    public int getTotalPrice(String username)
    {
        	Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        	if(siteUser.isPresent())
        	{
                return siteUser.get().getTotalPrice();
        	}
        	else
        	{
        		throw new DataNotFoundException("siteuser not found");
        	}
    }

    public List<SiteUser> findAllUsers()
    {
        return this.userRepository.findAll();
    }
//    public void updateCart(String username, int itemId, int amount) {
//        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
//
//        if (siteUser.isPresent()) {
//            SiteUser user = siteUser.get();
//            user.setCart(new Cart(orderItems));
//            this.userRepository.save(user);
//        } else {
//            throw new DataNotFoundException("siteuser not found");
//        }
//    }
}