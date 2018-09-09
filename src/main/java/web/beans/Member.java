package web.beans;

import java.util.HashSet;
import java.util.Set;

public class Member {
    private int id;
    private String name;
    private String pwd;
    private String sex;
    private int phone;
    private String email;
    private String question;
    private String answer;
    private Corporation corporation;
    private Set<Car> cars = new HashSet<Car>();
    private Set<Goods> goods = new HashSet<Goods>();
    private Set<DynamicInfo> infos = new HashSet<DynamicInfo>();
    private Set<Knowledge> knowledges = new HashSet<Knowledge>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<DynamicInfo> getInfos() {
        return infos;
    }

    public void setInfos(Set<DynamicInfo> infos) {
        this.infos = infos;
    }

    public Set<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(Set<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }
}
