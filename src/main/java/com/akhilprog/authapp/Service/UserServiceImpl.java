package com.akhilprog.authapp.Service;

import com.akhilprog.authapp.Repository.UserRepository;
import com.akhilprog.authapp.dtos.UserDto;
import com.akhilprog.authapp.entity.Provider;
import com.akhilprog.authapp.entity.User;
import com.akhilprog.authapp.exceptions.ResourceNotFounDException;
import com.akhilprog.authapp.helpers.UserHelper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import static java.util.Arrays.stream;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {

        if(userDto.getEmail() == null || userDto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is required");
        }

        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Email allready Exists");
        }
        User user= modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider()!=null ? userDto.getProvider() : Provider.LOCAL);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user= userRepository
                .findByEmail(email)
                .orElseThrow(()->new ResourceNotFounDException("User not found having this email id . "));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        UUID uid = UserHelper.parseUUID(userId);
        User existingUser = userRepository
                .findById(uid)
                .orElseThrow(()->new ResourceNotFounDException("user not found"));
        if(userDto.getName() != null)existingUser.setName(userDto.getName());
        //TODo:password logic for security ll do
        if(userDto.getPassword() != null)existingUser.setPassword(userDto.getPassword());
        if(userDto.getImage() != null)existingUser.setImage(userDto.getImage());
        if(userDto.getProvider() != null)existingUser.setProvider(userDto.getProvider());
        existingUser.setEnable(userDto.isEnable());
        existingUser.setUpdteAt(Instant.now());
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID uid=UserHelper.parseUUID(userId);
        User user = userRepository.findById(uid).orElseThrow(()-> new ResourceNotFounDException("user not found exception"));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(String userId){
        UUID uid = UserHelper.parseUUID(userId);
        User user = userRepository.findById(uid).orElseThrow(()->new ResourceNotFounDException("user Not Found"));
        return modelMapper.map(user, UserDto.class);
    }
    @Override
    @Transactional
    public Iterable<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }
}
