package somepackage.questionnairePageElements;

/**
 * Created by Andrity Zhuk on 12/28/2016.
 */
public enum InsureonElementTypes
{
    INPUT("input"),
    INPUTBOX("inputbox"),
    RNDDATAINPUTBOX("rnddatainputbox"),
    INPUTBOXWITHTAB("inputboxwithtab"),
    TEXTAREA("textarea"),
    SELECT("select"),
    DROPDOWNBOX("dropdownbox"),
    BUTTON("button"),
    CHECKBOX("checkbox"),
    REVERSEDCHECKBOX("reversedcheckbox"),
    YESNORADIOBUTTON("yesnoradiobutton"),
    ADDRESSRADIO("addressradio"),
    REVERSEDRADIO("reversedradio");

    private final String elementType;

    InsureonElementTypes(String elementType)
    {
        this.elementType = elementType;
    }

    @Override
    public String toString()
    {
        return this.elementType;
    }
}
