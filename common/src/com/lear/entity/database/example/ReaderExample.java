package com.lear.entity.database.example;

/**
 * 读者数据库排序筛选类
 * @author 天狗
 */
public class ReaderExample {

    private String readerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Integer limit;

    private String orderByClause;

    public String toSql() {
        StringBuilder builder = new StringBuilder("select * from reader where 1=1");
        if (readerId != null) {
            builder.append(" and reader_id = ").append(readerId);
        }
        if (firstName != null) {
            builder.append(" and first_name = ").append(firstName);
        }
        if (lastName != null) {
            builder.append(" and last_name = ").append(lastName);
        }
        if (address != null) {
            builder.append(" and address = ").append(address);
        }
        if (phoneNumber != null) {
            builder.append(" and phone_number = ").append(phoneNumber);
        }
        if (limit != null) {
            builder.append(" and limit = ").append(limit);
        }
        if (orderByClause != null) {
            builder.append(" order by ").append(orderByClause);
        }
        return builder.toString();
    }

    public ReaderExample() {
    }

    public String getReaderId() {
        return readerId;
    }

    public ReaderExample setReaderId(String readerId) {
        this.readerId = readerId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ReaderExample setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ReaderExample setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ReaderExample setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ReaderExample setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public ReaderExample setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public ReaderExample setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    @Override
    public String toString() {
        return "ReaderExample{" +
                "readerId='" + readerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", limit=" + limit +
                ", orderByClause='" + orderByClause + '\'' +
                '}';
    }
}
