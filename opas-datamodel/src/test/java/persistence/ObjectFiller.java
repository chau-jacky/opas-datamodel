package persistence;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import com.mysql.cj.core.util.StringUtils;

import model.security.User;

public class ObjectFiller {

	public static void main(String[] args) {

		User user = new User();

		Method[] methods = user.getClass().getMethods();

		for (int i = 0; i < methods.length; i++) {

			if (StringUtils.startsWithIgnoreCase(methods[i].getName(), "set")) {
				System.out.println("Method name: " + methods[i].getName());

				if (methods[i].getParameterCount() == 1) {

					Parameter[] parameters = methods[i].getParameters();

					Type type = parameters[0].getType();

					System.out.println(type);

					if (type == Long.TYPE) {
						System.out.println("FUCK YOU");

					}

				}
			}
		}

	}

}
