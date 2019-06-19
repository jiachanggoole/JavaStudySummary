package com.sunny;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaChang
 * @date 2019-05-05
 */
public class Test {

    public static class AA{
        private int id;

        private String name;

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

        @Override
        public String toString() {
            return "AA{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
       char x = 'x';
       int i = 0;
       System.out.println(true ? x:0);
       System.out.println(false ? i:x);
        System.out.println(x);

    }
}
