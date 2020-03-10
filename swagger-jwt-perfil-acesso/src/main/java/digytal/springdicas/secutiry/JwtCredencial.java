package digytal.springdicas.secutiry;

import java.util.HashSet;
import java.util.Set;

public class JwtCredencial {
	private String login;
	private Set<String>menus=new HashSet<String>();
    private String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getLogin() {
		return login;
	}
    public void setLogin(String login) {
		this.login = login;
	}
    public Set<String> getMenus() {
		return menus;
	}
    public void setMenus(Set<String> menus) {
		this.menus = menus;
	}
    
}
