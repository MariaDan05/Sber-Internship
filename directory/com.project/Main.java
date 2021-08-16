package com.project;

import com.project.model.City;
import com.project.service.CityManager;

import java.io.*;
import java.util.*;

import static com.project.service.CityManager.readFile;

public class Main {
    public static void main(String[] args) {

        List<City> cities = new ArrayList<>();
        try {
            cities = readFile("C:\\Users\\mariy\\OneDrive\\Desktop\\city_ru.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner s = new Scanner(System.in);
        boolean isMenuActive = true;
        while(isMenuActive) {
            System.out.println("Enter command number:");
            System.out.println("1. Напечатать список городов по наименованию в алфавитном порядке по убыванию без учета регистра.");
            System.out.println("2. Напечатать список городов по федеральному округу и наименованию города внутри каждого " +
                    "федерального округа в алфавитном порядке по убыванию с учетом регистра.");
            System.out.println("3. Напечатать индекс элемента и значение с наибольшим количеством жителей города.");
            System.out.println("4. Напечатать количество городов в разрезе регионов.");
            System.out.println("5. Выход.");

            int num = s.nextInt();
            switch (num) {
                case 1:
                    CityManager.printSortedNamesCaseInsensitive(cities);
                    break;
                case 2:
                    CityManager.printSortedDistrictsAndNames(cities);
                    break;
                case 3:
                    CityManager.printIndexAndElemMaxVal(cities);
                    break;
                case 4:
                    CityManager.printCityQuantityByRegion(cities);
                    break;
                case 5:
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Invalid command number.");
            }
            System.out.println();
        }
    }
}
