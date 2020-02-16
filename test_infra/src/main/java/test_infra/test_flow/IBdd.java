package test_infra.test_flow;

public interface IBdd {

	@Step(description = "Short explanation for the GIVEN part")
	void given() throws Exception;

	@Step(description = "Short explanation for the WHEN part")
	void when() throws Exception;

	@Step(description = "Short explanation for the THEN part")
	void then() throws Exception;
}
