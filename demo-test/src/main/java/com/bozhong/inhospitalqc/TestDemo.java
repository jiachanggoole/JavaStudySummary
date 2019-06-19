package com.bozhong.inhospitalqc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaChang
 * @date 2019-03-28
 */
public class TestDemo {

    public static class TwoQCPlanFrequencyInfoRespDTO {

        private int weekNum;

        private List<Long> indexData;

        public int getWeekNum() {
            return weekNum;
        }

        public void setWeekNum(int weekNum) {
            this.weekNum = weekNum;
        }

        public List<Long> getIndexData() {
            return indexData;
        }

        public void setIndexData(List<Long> indexData) {
            this.indexData = indexData;
        }
    }

    public static void main(String[] args) {


            int weekNum = 5;
            int indexListSize = 18;
            int everyWeekCount = indexListSize/weekNum;
            int yushu = indexListSize % weekNum;

            List<TwoQCPlanFrequencyInfoRespDTO> respDTOList = new ArrayList<>();


            // 当前的数
            int theWeekNum = 1;
            List<Long> indexRespDTOList = new ArrayList<>();


            // 所有的指标平均分配
            for (int i = 0; i < indexListSize; i++) {
                // 指标对象转换

                indexRespDTOList.add(Long.valueOf(i));

                int theWeekCount = theWeekNum <= yushu ? (everyWeekCount + 1) : everyWeekCount;
                // 前面的放平均数
                if(indexRespDTOList.size() == theWeekCount){
                    TwoQCPlanFrequencyInfoRespDTO respDTO = new TwoQCPlanFrequencyInfoRespDTO();
                    respDTO.setWeekNum(theWeekNum);
                    respDTO.setIndexData(Lists.newArrayList(indexRespDTOList));
                    respDTOList.add(respDTO);

                    theWeekNum ++;
                    indexRespDTOList.clear();
                }



            }


        System.out.println(JSON.toJSONString(respDTOList));

    }

}
