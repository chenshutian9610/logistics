package web.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member {
    private int id;
    private String name;
    private String pwd;
    private String sex;
    private String phone;
    private String email;
    private String question;
    private String answer;
    private Corporation corporation;
    private Set<Car> cars = new HashSet<Car>();
    private Set<Goods> goods = new HashSet<Goods>();
    private Set<DynamicInfo> infos = new HashSet<DynamicInfo>();
    private Set<Knowledge> knowledges = new HashSet<Knowledge>();

    @Id
    @GeneratedValue(generator = "my")
    @GenericGenerator(name = "my", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true)
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    @OneToMany(mappedBy = "member")
    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    @OneToMany(mappedBy = "member")
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @OneToMany(mappedBy = "member")
    public Set<DynamicInfo> getInfos() {
        return infos;
    }

    public void setInfos(Set<DynamicInfo> infos) {
        this.infos = infos;
    }

    @OneToMany(mappedBy = "member")
    public Set<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(Set<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }
}
