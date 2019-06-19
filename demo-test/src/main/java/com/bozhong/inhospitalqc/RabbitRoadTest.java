package com.bozhong.inhospitalqc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaChang
 * @date 2019-04-10
 */
public class RabbitRoadTest {

    public static class Point {

        private int x;

        private int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point add(Point addPoint){
            Point newPoint = new Point();
            newPoint.setX(x+addPoint.getX());
            newPoint.setY(y+addPoint.getY());
            return newPoint;
        }
        @Override
        public boolean equals(Object obj) {
            Point point = (Point) obj;
            return this.x == point.getX() && this.y == point.getY();
        }
    }

    /**
     * 传入一个数组，加入下一个点后，分裂成多个数组
     * @param everyList
     * @param addZuobiaoList
     * @return
     */
    public static List<List<Point>> spilt(List<Point> everyList, List<Point> addZuobiaoList){
        List<List<Point>> result = new ArrayList<>();

        for(Point addPoint:addZuobiaoList){
            Point newPoint = everyList.get(everyList.size()-1).add(addPoint);
            // 拷贝
            if(!everyList.contains(newPoint)){
                List<Point> copyList = new ArrayList<>();
                for (Point copyPoint: everyList) {
                    copyList.add(copyPoint);
                }
                copyList.add(newPoint);
                result.add(copyList);
            }
        }
        return result;

    }

    public static void main(String[] args) {

        int x = 0,y=0;

        int maxWay = 14;

        Point point = new Point(x,y);
        List<Point> everyList = new ArrayList<>();
        everyList.add(point);


        List<Point> addPointList = new ArrayList<>();
        addPointList.add(new Point(0,1));
        addPointList.add(new Point(0,-1));
        addPointList.add(new Point(1,0));
        addPointList.add(new Point(-1,0));


        List<List<Point>> totalList = new ArrayList<>();
        totalList.add(everyList);



        for (int i = 0; i < maxWay; i++) {

            List<List<Point>> newTotalList = new ArrayList<>();

            for(List<Point> pointList : totalList){
                List<List<Point>> newList = spilt(pointList,addPointList);
                newTotalList.addAll(newList);
            }

            totalList.clear();
            totalList = newTotalList;
        }

        System.out.println(totalList.size());

    }

}
