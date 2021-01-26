package n1k.spring_project.sup;

public enum UserSaveMethod {
	NEW("Save new user..."),
	UPDATE("Update existing user...");

	private final String annotation;

	UserSaveMethod(String annotation) {
		this.annotation = annotation;
	}

	public String getAnnotation() {
		return annotation;
	}

}//close UserSaveMethod
