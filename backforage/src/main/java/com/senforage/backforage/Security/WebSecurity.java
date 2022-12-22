package com.example.jwt.Security;

import com.example.jwt.Filter.AuthenticationFilter;
import com.example.jwt.Filter.AuthorizationFilter;
import com.example.jwt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Value("${jwtSecret}")
    private String jwtSecret;
    private UserDetailsService userDetailsService;
    @Autowired
    public WebSecurity(@Lazy UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        http.cors().and().csrf().disable()
                .addFilterBefore(new AuthorizationFilter(jwtSecret), LogoutFilter.class).authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/*").permitAll();

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), jwtSecret);
        authenticationFilter.setFilterProcessesUrl("/login");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/login/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").permitAll();
                //hasAnyAuthority("ROLE_ADMIN", "ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/**").permitAll();
                //hasAnyAuthority("ROLE_AGENT_SAISIE","ROLE_ADMIN");



        http.authorizeRequests().antMatchers(HttpMethod.GET, "/temoin/list/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/temoin/add/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/temoin/getById/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/temoin/delete/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/temoin/update/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/officier/list/**").hasAnyAuthority("ROLE_AGENT_SAISIE","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/officier/add/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/officier/getById/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/officier/delete/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT, "/officier/update/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/jugement/list/**").hasAnyAuthority("ROLE_AGENT_SAISIE","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/jugement/list/**").hasAnyAuthority("ROLE_AGENT_SAISIE","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/jugement/add/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/jugement/getById/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/jugement/delete/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT, "/jugement/update/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/centre/list/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/centre/add/**").permitAll();

       //http.authorizeRequests().antMatchers(HttpMethod.GET, "/centre/list/**").hasAnyAuthority("ROLE_AGENT_SAISIE","ROLE_ADMIN");
        // http.authorizeRequests().antMatchers(HttpMethod.POST, "/centre/add/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/centre/getById/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/centre/delete/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT, "/centre/update/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/formationsanitaire/list/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/formationsanitaire/add/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/formationsanitaire/getById/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/formationsanitaire/delete/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT, "/formationsanitaire/update/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/mariage/nbMar").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/mariage/list/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/mariage/getLastNumActe/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/mariage/add/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/mariage/getById/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/mariage/delete/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT, "/mariage/update/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_AGENT_SAISIE");


        http.authorizeRequests().antMatchers(HttpMethod.POST, "/deces/add").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/deces/list").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/acte/list").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/deces/getLastNumActe").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/naissance/getLastNumActe").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/naissance/add").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/naissance/list/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/naissance/nbNaiss").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/naissance/getById/{id}").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/registre/list/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/registre/add/**").permitAll();




        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return super.userDetailsService();
    }
}