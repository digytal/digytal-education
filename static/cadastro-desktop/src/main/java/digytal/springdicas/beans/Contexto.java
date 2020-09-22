package digytal.springdicas.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import open.digytal.util.desktop.Splash;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Contexto {
	private static ApplicationContext contexto;
	
	 @Autowired
    public Contexto(ApplicationContext contexto) {
		 Contexto.contexto = contexto;
    }
	public static <T> T getBean(Class classe) {
		return (T) contexto.getBean(classe);
	}
	private static Splash splash;
	public static void exibirSplash() {
		splash = new Splash();
		splash.setVisible(true);
	}

	public static void fecharSplash() {
		if (splash != null) {
			splash.dispose();
			splash=null;
		}
	}
}


/**
@Component
public class Boo {

    private static Foo foo;

    @Autowired
    public Boo(Foo foo) {
        Boo.foo = foo;
    }

    public static void randomMethod() {
         foo.doStuff();
    }
}
*/

/**

@Component
public class Boo {

    private static Foo foo;
    @Autowired
    private Foo tFoo;

    @PostConstruct
    public void init() {
        Boo.foo = tFoo;
    }

    public static void randomMethod() {
         foo.doStuff();
    }
}
 
 
 */

