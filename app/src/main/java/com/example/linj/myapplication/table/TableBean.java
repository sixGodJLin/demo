package com.example.linj.myapplication.table;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * @author JLin
 * @date 2019/11/6
 * @describe 表格数据
 */
@SmartTable()
public class TableBean {
    @SmartColumn(id = 1, name = "站点")
    private String name;
    @SmartColumn(id = 2, name = "品类")
    private String type;
    @SmartColumn(id = 3, name = "重量")
    private String weight;
    @SmartColumn(id = 4, name = "金额")
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
