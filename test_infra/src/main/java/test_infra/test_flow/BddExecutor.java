package test_infra.test_flow;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BddExecutor {
	private static Logger logger = LoggerFactory.getLogger(BddExecutor.class);

	static public void run(IBdd bdd) throws Exception {
		try {
			reportStepDescription(bdd, "given");
			bdd.given();

			reportStepDescription(bdd, "when");
			bdd.when();

			reportStepDescription(bdd, "then");
			bdd.then();

		} catch (Exception e) {
			throw (e);
		}
	}

	protected static void reportStepDescription(IBdd bdd, String stepName) {
		Class<? extends IBdd> aClass = bdd.getClass();
		try {
			final Method method = aClass.getMethod(stepName, new Class[] {});
			final Step step = method
					.getAnnotation(Step.class);
			if (null == step) {
				logger.info("Step description is not available");
			} else {
				logger.info(String.format("%s : %s", stepName.toUpperCase(), step.description()), true);
			}
		} catch (Exception e) {
			logger.error("Step defintion is not available");
		}
	}

}
