package com.example.linj.myapplication.bean;

import java.util.List;

/**
 * @author JLin
 * @date 2019/12/31
 * @describe
 */
public class BaiduGetNumberResponse {

    /**
     * log_id : 5478863238753484671
     * words_result_num : 3
     * words_result : [{"location":{"width":268,"top":345,"left":606,"height":23},"words":"543543777555638"},{"location":{"width":215,"top":453,"left":953,"height":30},"words":"17600987577"},{"location":{"width":213,"top":657,"left":953,"height":29},"words":"15798448856"}]
     */

    private String log_id;
    private int words_result_num;
    private List<WordsResultBean> words_result;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public List<WordsResultBean> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<WordsResultBean> words_result) {
        this.words_result = words_result;
    }

    public static class WordsResultBean {
        /**
         * location : {"width":268,"top":345,"left":606,"height":23}
         * words : 543543777555638
         */

        private LocationBean location;
        private String words;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public static class LocationBean {
            /**
             * width : 268
             * top : 345
             * left : 606
             * height : 23
             */

            private int width;
            private int top;
            private int left;
            private int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
