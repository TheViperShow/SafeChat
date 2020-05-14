package me.thevipershow.safechat.config;

public final class ValuesValidator {

    public static ValuesValidator instance = null;
    private final Values values;

    private ValuesValidator(final Values values) {
        this.values = values;
    }

    public static ValuesValidator getInstance(final Values values) {
        return instance != null ? instance : (instance = new ValuesValidator(values));
    }

    public boolean validateAll() {
        boolean dbtype = Validator.validate(values.getDatabase().toLowerCase(), Throwable::printStackTrace, "SQLITE", "POSTGRESQL");
        boolean portCheck = Validator.validateInRange(values.getPort(), Throwable::printStackTrace, Validator.NumberRange.process(0, 65535));
        boolean nullCheckBlacklistWords = Validator.validateNotNull(values.getBlacklistWords(), Throwable::printStackTrace);
        boolean nullCheckDomainHover = Validator.validateNotNull(values.getDomainHover(), Throwable::printStackTrace);
        boolean nullCheckDomainWarning = Validator.validateNotNull(values.getDomainWarning(), Throwable::printStackTrace);
        boolean nullCheckWordsWarning = Validator.validateNotNull(values.getWordsWarning(), Throwable::printStackTrace);
        boolean nullCheckWordsHover = Validator.validateNotNull(values.getWordsHover(), Throwable::printStackTrace);
        return dbtype && portCheck && nullCheckBlacklistWords && nullCheckDomainHover && nullCheckDomainWarning && nullCheckWordsHover && nullCheckWordsWarning;
    }
}