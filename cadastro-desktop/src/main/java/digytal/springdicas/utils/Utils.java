package digytal.springdicas.utils;

import java.lang.reflect.Method;

import digytal.springdicas.api.model.cadastro.Pessoa;

public class Utils {
	public static void main(String[] args) {
		gerarGetSet("entidade", Pessoa.class);
	}
	private static void gerarGetSet(String var, Class classe) {
		Method[] mt = classe.getDeclaredMethods();
		for(Method m: mt) {
			if(m.getName().startsWith("set")) {
				System.out.println(var + "."+m.getName() + "(c" + m.getName().replaceAll("set", "") + ".getText());");
			}
		}
		System.out.println("\n\n\n");
		for(Method m: mt) {
			if(m.getName().startsWith("get")) {
				String name = m.getName().replaceAll("get", "");
				System.out.println("c" + name + ".setText(" + var + "." +m.getName() + "());");
			}
		}
	}
}
