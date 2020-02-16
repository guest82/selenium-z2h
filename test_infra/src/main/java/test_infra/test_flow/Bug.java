package test_infra.test_flow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Bug {

	public String id();

	public String suite();

	public String comment();

}
