package nam.gor.machineryleasing.models.annotations;

public interface RegexPattern {
    String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+" +
            "(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+" +
            "(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
}
