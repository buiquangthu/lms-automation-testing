package vn.oes.utils;

public class XPathUtils {


    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁẢÃẠÂẦẤẨẪẬĂẰẮẲẴẶÈÉẺẼẸÊỀẾỂỄỆÌÍỈĨỊÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢÙÚỦŨỤƯỪỨỬỮỰỲÝỶỸỴ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyzàáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ";

    public static String toXPathLiteral(String s) {
        if (!s.contains("'")) return "'" + s + "'";
        if (!s.contains("\"")) return "\"" + s + "\"";
        return "concat('" + s.replace("'", "',\"'\",'") + "')";
    }
    //TẠO XPATH CASE INSENSITIVE CHO TEXT
    public static String createCaseInsensitiveTextXPath(String element, String searchText) {
        String literal = toXPathLiteral(searchText);
        return String.format(
                "//%s[translate(text(), '%s', '%s') = translate(%s, '%s', '%s')]",
                element, UPPER_CASE, LOWER_CASE, literal, UPPER_CASE, LOWER_CASE
        );
    }

    // TẠO XPATH CASE INSENSITIVE CHO ATTRIBUTE
    public static String createCaseInsensitiveAttrXPath(String element, String attribute, String searchText) {
        String literal = toXPathLiteral(searchText);
        return String.format(
                "//%s[translate(@%s, '%s', '%s') = translate(%s, '%s', '%s')]",
                element, attribute, UPPER_CASE, LOWER_CASE, literal, UPPER_CASE, LOWER_CASE
        );
    }

    // TẠO XPATH CONTAINS CASE INSENSITIVE
    public static String createCaseInsensitiveContainsXPath(String element, String searchText) {
        String literal = toXPathLiteral(searchText.toLowerCase());
        return String.format(
                "//%s[contains(translate(text(), '%s', '%s'), %s)]",
                element, UPPER_CASE, LOWER_CASE, literal
        );
    }

    //TẠO XPATH CONTAINS CHO ATTRIBUTE
    public static String createCaseInsensitiveContainsAttrXPath(String element, String attribute, String searchText) {
        String literal = toXPathLiteral(searchText.toLowerCase());
        return String.format(
                "//%s[contains(translate(@%s, '%s', '%s'), %s)]",
                element, attribute, UPPER_CASE, LOWER_CASE, literal
        );
    }

    //XPATH THÔNG MINH - TỰ ĐỘNG CHỌN EXACT HOẶC CONTAINS
    public static String createSmartCaseInsensitiveXPath(String element, String searchText, boolean exactMatch) {
        if (exactMatch) {
            return createCaseInsensitiveTextXPath(element, searchText);
        } else {
            return createCaseInsensitiveContainsXPath(element, searchText);
        }
    }

    // XPATH CHO NHIỀU ATTRIBUTE (title, data-name, text, etc.)
    public static String createMultiAttributeCaseInsensitiveXPath(String element, String searchText, String... attributes) {
        StringBuilder xpath = new StringBuilder("//" + element + "[");
        String literal = toXPathLiteral(searchText);

        for (int i = 0; i < attributes.length; i++) {
            if (i > 0) xpath.append(" or ");

            if ("text()".equals(attributes[i])) {
                xpath.append(String.format(
                        "translate(text(), '%s', '%s') = translate(%s, '%s', '%s')",
                        UPPER_CASE, LOWER_CASE, literal, UPPER_CASE, LOWER_CASE
                ));
            } else {
                xpath.append(String.format(
                        "translate(@%s, '%s', '%s') = translate(%s, '%s', '%s')",
                        attributes[i], UPPER_CASE, LOWER_CASE, literal, UPPER_CASE, LOWER_CASE
                ));
            }
        }
        xpath.append("]");
        return xpath.toString();
    }

    // NORMALIZE TIẾNG VIỆT (loại bỏ dấu)
    public static String normalizeVietnamese(String text) {
        return text.replaceAll("[àáảãạâầấẩẫậăằắẳẵặ]", "a")
                .replaceAll("[ÀÁẢÃẠÂẦẤẨẪẬĂẰẮẲẴẶ]", "A")
                .replaceAll("[èéẻẽẹêềếểễệ]", "e")
                .replaceAll("[ÈÉẺẼẸÊỀẾỂỄỆ]", "E")
                .replaceAll("[ìíỉĩị]", "i")
                .replaceAll("[ÌÍỈĨỊ]", "I")
                .replaceAll("[òóỏõọôồốổỗộơờớởỡợ]", "o")
                .replaceAll("[ÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢ]", "O")
                .replaceAll("[ùúủũụưừứửữự]", "u")
                .replaceAll("[ÙÚỦŨỤƯỪỨỬỮỰ]", "U")
                .replaceAll("[ỳýỷỹỵ]", "y")
                .replaceAll("[ỲÝỶỸỴ]", "Y")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D");
    }

    // 8. XPATH NORMALIZED (không dấu) CHO TIẾNG VIỆT
    public static String createNormalizedVietnameseXPath(String element, String searchText) {
        String normalizedSearch = normalizeVietnamese(searchText.toLowerCase());
        String literal = toXPathLiteral(normalizedSearch);

        return String.format(
                "//%s[contains(translate(translate(text(), " +
                        "'àáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵđ', " +
                        "'aaaaaaaaaaaaaaaeeeeeeeeeeiiiiioooooooooooooooouuuuuuuuuuyyyyyd'), " +
                        "'%s', '%s'), %s)]",
                UPPER_CASE, LOWER_CASE, literal
        );
    }

    // 9. BUILDER PATTERN CHO XPATH PHỨC TẠP
    public static class XPathBuilder {
        private String element;
        private String searchText;
        private boolean caseInsensitive = false;
        private boolean exactMatch = true;
        private boolean normalizeVietnamese = false;
        private String attribute = "text()";

        public XPathBuilder(String element, String searchText) {
            this.element = element;
            this.searchText = searchText;
        }

        public XPathBuilder caseInsensitive() {
            this.caseInsensitive = true;
            return this;
        }

        public XPathBuilder contains() {
            this.exactMatch = false;
            return this;
        }

        public XPathBuilder attribute(String attr) {
            this.attribute = attr;
            return this;
        }

        public XPathBuilder normalizeVietnamese() {
            this.normalizeVietnamese = true;
            return this;
        }

        public String build() {
            if (normalizeVietnamese) {
                return createNormalizedVietnameseXPath(element, searchText);
            }

            if (caseInsensitive) {
                if ("text()".equals(attribute)) {
                    return exactMatch ?
                            createCaseInsensitiveTextXPath(element, searchText) :
                            createCaseInsensitiveContainsXPath(element, searchText);
                } else {
                    return exactMatch ?
                            createCaseInsensitiveAttrXPath(element, attribute, searchText) :
                            createCaseInsensitiveContainsAttrXPath(element, attribute, searchText);
                }
            }

            // Fallback to basic xpath
            String literal = toXPathLiteral(searchText);
            if ("text()".equals(attribute)) {
                return exactMatch ?
                        String.format("//%s[text()=%s]", element, literal) :
                        String.format("//%s[contains(text(),%s)]", element, literal);
            } else {
                return exactMatch ?
                        String.format("//%s[@%s=%s]", element, attribute, literal) :
                        String.format("//%s[contains(@%s,%s)]", element, attribute, literal);
            }
        }
    }

    // 10. STATIC METHODS ĐỂ SỬ DỤNG DỄ DÀNG
    public static XPathBuilder xpath(String element, String searchText) {
        return new XPathBuilder(element, searchText);
    }

    // METHOD ĐẶC BIỆT CHO NORMALIZE-SPACE CASE INSENSITIVE
    public static String createNormalizeSpaceCaseInsensitiveXPath(String element, String searchText) {
        String literal = toXPathLiteral(searchText);
        return String.format(
                "//%s[translate(normalize-space(), '%s', '%s') = translate(%s, '%s', '%s')]",
                element, UPPER_CASE, LOWER_CASE, literal, UPPER_CASE, LOWER_CASE
        );
    }

}
