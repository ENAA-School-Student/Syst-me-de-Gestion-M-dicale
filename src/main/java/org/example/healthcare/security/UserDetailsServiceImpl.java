package org.example.healthcare.security;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

private final UserRepository userRepository;
@Override
    public UserDetails loadUserByUsername(String email){
    return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user note found"));
}
}