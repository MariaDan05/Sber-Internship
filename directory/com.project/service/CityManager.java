package com.project.service;

import com.project.model.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CityManager {
    public static List<City> readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<City> citiesList = new LinkedList<>();
        while ((line = reader.readLine()) != null) {
            City city = new City();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 1)
                    city.setName(data);
                else if (index == 2)
                    city.setRegion(data);
                else if (index == 3)
                    city.setDistrict(data);
                else if (index == 4)
                    city.setPopulation(Integer.parseInt(data));
                else if (index == 5)
                    city.setFoundation(data);
                index++;
            }
            index = 0;
            citiesList.add(city);
        }
        reader.close();
        return citiesList;
    }

    //Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра
    public static void printSortedNamesCaseInsensitive(List<City> cities) {
        List<City> sorted = cities
            .stream()
            .sorted(Comparator.comparing(City::getName, String.CASE_INSENSITIVE_ORDER))
            .collect(Collectors.toList());
        for (City city : sorted) {
            System.out.println(city);
        }
    }

    //Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа в
    //алфавитном порядке по убыванию с учетом регистра
    public static void printSortedDistrictsAndNames(List<City> cities) {
        List<City> sorted = cities
            .stream()
            .sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
            .collect(Collectors.toList());
        for (City city : sorted) {
            System.out.println(city);
        }
    }

    //Напечатать индекс элемента и значение с наибольшим количеством жителей города
    public static void printIndexAndElemMaxVal(List<City> cities){
        int maxPopulation = cities.get(0).getPopulation();
        int index = 0;
        for (int i = 1; i < cities.size(); i++) {
            if(cities.get(i).getPopulation() > maxPopulation) {
                maxPopulation = cities.get(i).getPopulation();
                index = i;
            }
        }
        System.out.println("[" + index +"] = " + maxPopulation);
    }

    //Напечатать количество городов в разрезе регионов.
    public static void printCityQuantityByRegion(List<City> cities) {

        List<String> regions = new LinkedList<>();

        for (City city : cities) {
            regions.add(city.getRegion());
        }

        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String s: regions)
        {
            Integer count = frequencyMap.get(s);
            if (count == null) {
                count = 0;
            }
            frequencyMap.put(s, count + 1);
        }

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }

}
