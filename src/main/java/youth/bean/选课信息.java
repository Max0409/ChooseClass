package youth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
* @author:MAX
*/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u9009\u8bfe\u4fe1\u606f", propOrder = {
        "\u5f00\u8bfe\u9662\u7cfb",
        "\u5b66\u53f7",
        "\u7f16\u53f7",
        "\u5f97\u5206",

})
public class  选课信息{

    @XmlElement(required = true)
    private String 开课院系;
    @XmlElement(required = true)
    private String 得分;
    @XmlElement(required = true)
    private String 学号;

    public String get学号() {
        return 学号;
    }

    public void set学号(String 学号) {
        this.学号 = 学号;
    }

    public String get得分() {
        return 得分;
    }

    public void set得分(String 得分) {
        this.得分 = 得分;
    }

    public String get开课院系() {
        return 开课院系;
    }

    public void set开课院系(String 开课院系) {
        this.开课院系 = 开课院系;
    }


}


