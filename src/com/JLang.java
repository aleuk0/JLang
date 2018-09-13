package com;
/*
�������� ������. �	������	������	���������	������������	�����	N (1 ? N ? 105) � ����������	����	�	���������
�������.	������	��	���������	N �����	��������	�����	wi (��������	������������������	��������
���������	����	������	��	�����	15)	�	�����	�����	ni (1 ? ni ? 106) � �����	���,	�������
�����������	���	�����	�	�������.	�����	�	�����	���������	������������	��������.	��	����
�����	��	�����������	�����	������	����.	�	(N + 2)-�	������	���������	�����	M (1 ? M ? 15000).	�
���������	M �������	����������	�����	ui (��������	������������������	��������	���������	����
������	��	�����	15) � ������	����,	���������	�������������.

���������. ���	������	��	M �����	����������	�������	��������	�����	�������������	��������	�����,
������������	�	ui,	�	�������	��������	�������.	�	������	����������	������	�����	����������
�����������	��	��������.	����	����������	������	������	���������	���������,	��	�������
�����	����	������	������	��	���.	��������	����������	���	�������	�����	����������
���������	����������	�����.

�������	������ ��������	���������	�����������:
� ���������	������	��������	����������	�����������.
� �������	������	��������	���������	�	�����������	������	�����	(����	�	����������).
���������	������	��������	�����	�	�����������	�����	������	(�����	��	�����).
� ���������	������	��������	������	��	������,	�������	�������	�������	������.
��������	����������� ���	�����	(��������	N:�)	��	�����.	�����	��	�����	�������
�������	�������	�	�����	������	���������.
� ������	���	������
� ���	��	Java �	�����,	���������������	�������������
https://googlestyleguide.googlecode.com/svn/trunk/javaguide.html
� �������	������	��������	������	(��	������	1-10	������)	��	��������	�����	test.in,
�������������	�	�������.
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
        // 1.1 ��� ����������� ���������� ������������� �� ��������. ��� ���������� ����������� sort().
        // 1.2 ������������� �� ���������� ����������. -> ������, ����������� � ��������� �������.
        // 1.3 ��������� ���������� M ����. ������ "�����" �� M ������������ � ������� �� ������� (�������:"���������� � ..."),
        // ������ ������ ���������� ��������� �� ����� � ����� ������.
                
        for (int i = N + 2; i < N + 2 + M; i++) {
            String a = arrayList.get(i);
            //System.out.println(a); // check values from list (all �)
			
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
