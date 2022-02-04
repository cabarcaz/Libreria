package guiamvc.ejercicio1.configuracion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import guiamvc.ejercicio1.util.LoginSuccessMessage;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {
  

  @Autowired
  private DataSource dataSource;
  
  @Autowired
  private BCryptPasswordEncoder passEnconder;

  @Autowired
  private LoginSuccessMessage loginSuccessMessage;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
    http.authorizeRequests()
    .antMatchers("/","/css/**","/images/**","/js/**","/contacto/","/autor/","/libro/","/editorial/","/libro/detalle/**").permitAll()
    .anyRequest().authenticated()
    .and()
    .formLogin()
      .successHandler(loginSuccessMessage)
      .loginPage("/login")
    .permitAll()
    .and()
    .logout().permitAll();
  }

  @Autowired
  public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
    builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passEnconder).usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?").authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.user_id=u.id WHERE u.username=?");
  }

}
