package co.kr.paka.domain;

public enum BoardType implements BaseCodeLabelEnum {
    NOTICE("공지사항"),
    FAQ("자주묻는질문"),
    INQUIRY("1:1문의");

    private String code;
    private String label;

    BoardType(String label) {
        this.code = name();
        this.label = label;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }
}
