package com;
/*
Исходные данные. В	первой	строке	находится	единственное	число	N (1 ? N ? 105) — количество	слов	в	найденных
текстах.	Каждая	из	следующих	N строк	содержит	слово	wi (непустая	последовательность	строчных
латинских	букв	длиной	не	более	15)	и	целое	число	ni (1 ? ni ? 106) — число	раз,	которое
встречается	это	слово	в	текстах.	Слово	и	число	разделены	единственным	пробелом.	Ни	одно
слово	не	повторяется	более	одного	раза.	В	(N + 2)-й	строке	находится	число	M (1 ? M ? 15000).	В
следующих	M строках	содержатся	слова	ui (непустая	последовательность	строчных	латинских	букв
длиной	не	более	15) — начала	слов,	введенных	пользователем.

Результат. Для	каждой	из	M строк	необходимо	вывести	наиболее	часто	употребляемые	японские	слова,
начинающихся	с	ui,	в	порядке	убывания	частоты.	В	случае	совпадения	частот	слова	необходимо
сортировать	по	алфавиту.	Если	существует	больше	десяти	возможных	вариантов,	то	вывести
нужно	лишь	первые	десять	из	них.	Варианты	дополнения	для	каждого	слова	необходимо
разделять	переводами	строк.

Решение	должно отвечать	следующим	требованиям:
• Программа	должна	являться	консольным	приложением.
• Входные	данные	подаются	программе	в	стандартном	потоке	ввода	(ввод	с	клавиатуры).
Программа	должна	выводить	ответ	в	стандартный	поток	вывода	(вывод	на	экран).
• Программа	должна	выводить	только	те	данные,	которые	требует	условие	задачи.
Выводить	приглашение для	ввода	(«Введите	N:»)	не	нужно.	Также	не	нужно	ожидать
нажатия	клавиши	в	конце	работы	программы.
• Скрипт	для	сборки
• Код	на	Java в	стиле,	соответствующем	рекомендациям
https://googlestyleguide.googlecode.com/svn/trunk/javaguide.html
• Решение	должно	работать	быстро	(не	больше	1-10	секунд)	на	тестовом	файле	test.in,
прилагающемся	к	заданию.
*/

import java.io.*;
import java.util.*;

public class JLang {

    public static void main(String[] args) throws IOException {
        // 0. from file to map

        // from file ArrayList
        File testFile = new File("test1.in");
        BufferedReader fin = new BufferedReader(new FileReader(testFile));
        String line;
        ArrayList<String> arrayList = new ArrayList<>();
        while ((line = fin.readLine()) != null) {
            //System.out.println(line); // check list
            arrayList.add(line);
        }
        fin.close();

        int N = Integer.parseInt(arrayList.get(0));
        //System.out.println(N);  // mb, better "return N;"
        int M = Integer.parseInt(arrayList.get(N + 1));
        //System.out.println(M);

        // from list to map
        Map <String, Integer> hashMap = new HashMap<>();

        for (int i = 1; i < N + 1; i++) {
            //System.out.println(arrayList.get(i)); // check range

            String[] arrayL = arrayList.get(i).split(" ");
            //System.out.println(arrayL[0]);    // check split
            //System.out.println(arrayL[1]);
            hashMap.put(arrayL[0], Integer.parseInt(arrayL[1]));    // put values after split to the map
        }
        // check map:
        //for (Map.Entry <String, Integer> pair : hashMap.entrySet()) System.out.println(pair.getKey() + " " + pair.getValue());

        // 1. Sorting (lambda, but may use sort dict or massive of objects)
        // 1.1 Для одинакового количества отсортировать по алфавиту. Для сортировки попробовать sort().
        // 1.2 Отсортировать по количеству применений. -> важнее, сортировать в последнюю очередь.
        // 1.3 Выгрузить оставшиеся M слов. Каждое "слово" из M сравнивается с ключами из словаря (функция:"начинается с ..."),
        // первые десять совпадений выводятся на экран с новой строки.
                
        for (int i = N + 2; i < N + 2 + M; i++) {
            String a = arrayList.get(i);
            //System.out.println(a); // check values from list (all М)
			
            hashMap
                    .entrySet()
                    .stream()
                    .filter(v -> v.getKey().startsWith(a))
                    //.sorted(Map.Entry.comparingByKey())     // first key, than value
                    .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
                    .limit(10)
                    .forEach(entry -> System.out.println(entry.getKey()));
            System.out.println();
        }
    }
}
