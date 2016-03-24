/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.citringo.hageplugin;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author pokem
 */
class AAA
{
    private static final Map<String, String> map1;
    private static final Map<String, String> map2;
    private static final Map<String, String> map3;
    private static final Map<String, String> map4;
    private static final Pattern AAE;
    private static final String[] ABB = {"A", "I", "U", "E", "O"};
    private static void ABA(String str)
    {
        for(String ABC : ABB)
        {
            map3.put((str + str + ABC).toLowerCase(), "っ" + map2.get((str + ABC).toLowerCase()));
        }
    }
    private static void ABD(String str)
    {
        for(String ABC : ABB)
        {
            map4.put((str.substring(0, 1) + str + ABC).toLowerCase(), "っ" + map3.get((str + ABC).toLowerCase()));
        }
    }
    static
    {
        AAE = Pattern.compile("n(.|$)");
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        map3 = new HashMap<>();
        map4 = new HashMap<>();
        map1.put("-".toLowerCase(), "ー");
        map1.put("A".toLowerCase(), "あ");
        map1.put("I".toLowerCase(), "い");
        map1.put("U".toLowerCase(), "う");
        map1.put("E".toLowerCase(), "え");
        map1.put("O".toLowerCase(), "お");
        map2.put("KA".toLowerCase(), "か");
        map2.put("KI".toLowerCase(), "き");
        map2.put("KU".toLowerCase(), "く");
        map2.put("KE".toLowerCase(), "け");
        map2.put("KO".toLowerCase(), "こ");
        map2.put("SA".toLowerCase(), "さ");
        map2.put("SI".toLowerCase(), "し");
        map2.put("SU".toLowerCase(), "す");
        map2.put("SE".toLowerCase(), "せ");
        map2.put("SO".toLowerCase(), "そ");
        map2.put("TA".toLowerCase(), "た");
        map2.put("TI".toLowerCase(), "ち");
        map2.put("TU".toLowerCase(), "つ");
        map2.put("TE".toLowerCase(), "て");
        map2.put("TO".toLowerCase(), "と");
        map2.put("NA".toLowerCase(), "な");
        map2.put("NI".toLowerCase(), "に");
        map2.put("NU".toLowerCase(), "ぬ");
        map2.put("NE".toLowerCase(), "ね");
        map2.put("NO".toLowerCase(), "の");
        map2.put("HA".toLowerCase(), "は");
        map2.put("HI".toLowerCase(), "ひ");
        map2.put("HU".toLowerCase(), "ふ");
        map2.put("HE".toLowerCase(), "へ");
        map2.put("HO".toLowerCase(), "ほ");
        map2.put("FA".toLowerCase(), "ふぁ");
        map2.put("FI".toLowerCase(), "ふぃ");
        map2.put("FU".toLowerCase(), "ふ");
        map2.put("FE".toLowerCase(), "ふぇ");
        map2.put("FO".toLowerCase(), "ふぉ");
        ABA("F");
        map2.put("MA".toLowerCase(), "ま");
        map2.put("MI".toLowerCase(), "み");
        map2.put("MU".toLowerCase(), "む");
        map2.put("ME".toLowerCase(), "め");
        map2.put("MO".toLowerCase(), "も");
        map2.put("YA".toLowerCase(), "や");
        map2.put("YI".toLowerCase(), "い");
        map2.put("YU".toLowerCase(), "ゆ");
        map2.put("YE".toLowerCase(), "いぇ");
        map2.put("YO".toLowerCase(), "よ");
        map2.put("RA".toLowerCase(), "ら");
        map2.put("RI".toLowerCase(), "り");
        map2.put("RU".toLowerCase(), "る");
        map2.put("RE".toLowerCase(), "れ");
        map2.put("RO".toLowerCase(), "ろ");
        map2.put("WA".toLowerCase(), "わ");
        map2.put("WI".toLowerCase(), "うぃ");
        map2.put("WU".toLowerCase(), "う");
        map2.put("WE".toLowerCase(), "うぇ");
        map2.put("WO".toLowerCase(), "を");
        map2.put("NN".toLowerCase(), "ん");
        map2.put("GA".toLowerCase(), "が");
        map2.put("GI".toLowerCase(), "ぎ");
        map2.put("GU".toLowerCase(), "ぐ");
        map2.put("GE".toLowerCase(), "げ");
        map2.put("GO".toLowerCase(), "ご");
        ABA("G");
        map2.put("ZA".toLowerCase(), "ざ");
        map2.put("ZI".toLowerCase(), "じ");
        map2.put("ZU".toLowerCase(), "ず");
        map2.put("ZE".toLowerCase(), "ぜ");
        map2.put("ZO".toLowerCase(), "ぞ");
        ABA("Z");
        map2.put("DA".toLowerCase(), "だ");
        map2.put("DI".toLowerCase(), "ぢ");
        map2.put("DU".toLowerCase(), "づ");
        map2.put("DE".toLowerCase(), "で");
        map2.put("DO".toLowerCase(), "ど");
        ABA("D");
        map2.put("BA".toLowerCase(), "ば");
        map2.put("BI".toLowerCase(), "び");
        map2.put("BU".toLowerCase(), "ぶ");
        map2.put("BE".toLowerCase(), "べ");
        map2.put("BO".toLowerCase(), "ぼ");
        ABA("B");
        map2.put("PA".toLowerCase(), "ぱ");
        map2.put("PI".toLowerCase(), "ぴ");
        map2.put("PU".toLowerCase(), "ぷ");
        map2.put("PE".toLowerCase(), "ぺ");
        map2.put("PO".toLowerCase(), "ぽ");
        ABA("P");
        map2.put("VA".toLowerCase(), "ヴぁ");
        map2.put("VI".toLowerCase(), "ヴぃ");
        map2.put("VU".toLowerCase(), "ヴ");
        map2.put("VE".toLowerCase(), "ヴぇ");
        map2.put("VO".toLowerCase(), "ヴぉ");
        ABA("V");
        map2.put("CA".toLowerCase(), "か");
        map2.put("CI".toLowerCase(), "し");
        map2.put("CU".toLowerCase(), "く");
        map2.put("CE".toLowerCase(), "せ");
        map2.put("CO".toLowerCase(), "こ");
        ABA("C");
        map2.put("JA".toLowerCase(), "じゃ");
        map2.put("JI".toLowerCase(), "じ");
        map2.put("JU".toLowerCase(), "じゅ");
        map2.put("JE".toLowerCase(), "じぇ");
        map2.put("JO".toLowerCase(), "じょ");
        ABA("J");
        map3.put("SHA".toLowerCase(), "しゃ");
        map3.put("SHI".toLowerCase(), "し");
        map3.put("SHU".toLowerCase(), "しゅ");
        map3.put("SHE".toLowerCase(), "しぇ");
        map3.put("SHO".toLowerCase(), "しょ");
        ABD("SH");
        map3.put("CHA".toLowerCase(), "ちゃ");
        map3.put("CHI".toLowerCase(), "ち");
        map3.put("CHU".toLowerCase(), "ちゅ");
        map3.put("CHE".toLowerCase(), "ちぇ");
        map3.put("CHO".toLowerCase(), "ちょ");
        ABD("CH");
        map3.put("THA".toLowerCase(), "てゃ");
        map3.put("THI".toLowerCase(), "てぃ");
        map3.put("THU".toLowerCase(), "てゅ");
        map3.put("THE".toLowerCase(), "てぇ");
        map3.put("THO".toLowerCase(), "てょ");
        ABD("TH");
        map3.put("TSA".toLowerCase(), "つぁ");
        map3.put("TSI".toLowerCase(), "つぃ");
        map3.put("TSU".toLowerCase(), "つ");
        map3.put("TSE".toLowerCase(), "つぇ");
        map3.put("TSO".toLowerCase(), "つぉ");
        ABD("TS");
        map3.put("KYA".toLowerCase(), "きゃ");
        map3.put("KYI".toLowerCase(), "きぃ");
        map3.put("KYU".toLowerCase(), "きゅ");
        map3.put("KYE".toLowerCase(), "きぇ");
        map3.put("KYO".toLowerCase(), "きょ");
        ABD("KY");
        map3.put("SYA".toLowerCase(), "しゃ");
        map3.put("SYI".toLowerCase(), "しぃ");
        map3.put("SYU".toLowerCase(), "しゅ");
        map3.put("SYE".toLowerCase(), "しぇ");
        map3.put("SYO".toLowerCase(), "しょ");
        ABD("SY");
        map3.put("TYA".toLowerCase(), "ちゃ");
        map3.put("TYI".toLowerCase(), "ちぃ");
        map3.put("TYU".toLowerCase(), "ちゅ");
        map3.put("TYE".toLowerCase(), "ちぇ");
        map3.put("TYO".toLowerCase(), "ちょ");
        ABD("TY");
        map3.put("NYA".toLowerCase(), "にゃ");
        map3.put("NYI".toLowerCase(), "にぃ");
        map3.put("NYU".toLowerCase(), "にゅ");
        map3.put("NYE".toLowerCase(), "にぇ");
        map3.put("NYO".toLowerCase(), "にょ");
        ABD("NY");
        map3.put("HYA".toLowerCase(), "ひゃ");
        map3.put("HYI".toLowerCase(), "ひぃ");
        map3.put("HYU".toLowerCase(), "ひゅ");
        map3.put("HYE".toLowerCase(), "ひぇ");
        map3.put("HYO".toLowerCase(), "ひょ");
        ABD("HY");
        map3.put("RYA".toLowerCase(), "りゃ");
        map3.put("RYI".toLowerCase(), "りぃ");
        map3.put("RYU".toLowerCase(), "りゅ");
        map3.put("RYE".toLowerCase(), "りぇ");
        map3.put("RYO".toLowerCase(), "りょ");
        ABD("RY");
        map3.put("GYA".toLowerCase(), "ぎゃ");
        map3.put("GYI".toLowerCase(), "ぎぃ");
        map3.put("GYU".toLowerCase(), "ぎゅ");
        map3.put("GYE".toLowerCase(), "ぎぇ");
        map3.put("GYO".toLowerCase(), "ぎょ");
        ABD("GY");
        map3.put("ZYA".toLowerCase(), "じゃ");
        map3.put("ZYI".toLowerCase(), "じぃ");
        map3.put("ZYU".toLowerCase(), "じゅ");
        map3.put("ZYE".toLowerCase(), "じぇ");
        map3.put("ZYO".toLowerCase(), "じょ");
        ABD("ZY");
        map3.put("DYA".toLowerCase(), "ぢゃ");
        map3.put("DYI".toLowerCase(), "ぢぃ");
        map3.put("DYU".toLowerCase(), "ぢゅ");
        map3.put("DYE".toLowerCase(), "ぢぇ");
        map3.put("DYO".toLowerCase(), "ぢょ");
        ABD("DY");
        map3.put("BYA".toLowerCase(), "びゃ");
        map3.put("BYI".toLowerCase(), "びぃ");
        map3.put("BYU".toLowerCase(), "びゅ");
        map3.put("BYE".toLowerCase(), "びぇ");
        map3.put("BYO".toLowerCase(), "びょ");
        ABD("BY");
        map3.put("PYA".toLowerCase(), "ぴゃ");
        map3.put("PYI".toLowerCase(), "ぴぃ");
        map3.put("PYU".toLowerCase(), "びゅ");
        map3.put("PYE".toLowerCase(), "ぴぇ");
        map3.put("PYO".toLowerCase(), "ぴょ");
        ABD("PY");
        //map2.put("TT".toLowerCase(), "っT");
        ABA("K");
        ABA("S");
        ABA("T");
        ABA("N");
        ABA("H");
        ABA("M");
        ABA("Y");
        ABA("R");
        ABA("W");

        map3.put("TTA".toLowerCase(), "った");
        map3.put("TTI".toLowerCase(), "っち");
        map3.put("TTU".toLowerCase(), "っつ");
        map3.put("TTE".toLowerCase(), "って");
        map3.put("TTO".toLowerCase(), "っと");
        ABD("TT");
        map2.put("XA".toLowerCase(), "ぁ");
        map2.put("XI".toLowerCase(), "ぃ");
        map2.put("XU".toLowerCase(), "ぅ");
        map2.put("XE".toLowerCase(), "ぇ");
        map2.put("XO".toLowerCase(), "ぉ");
        ABA("X");
        map3.put("XTU".toLowerCase(), "っ");
        map4.put("XXTU".toLowerCase(), "っっ");
        map3.put("XYA".toLowerCase(), "ゃ");
        map4.put("XXYA".toLowerCase(), "っゃ");
        map3.put("XYU".toLowerCase(), "ゅ");
        map4.put("XXYU".toLowerCase(), "っゅ");
        map3.put("XYO".toLowerCase(), "ょ");
        map4.put("XXYO".toLowerCase(), "っょ");
        map2.put("LA".toLowerCase(), "ぁ");
        map2.put("LI".toLowerCase(), "ぃ");
        map2.put("LU".toLowerCase(), "ぅ");
        map2.put("LE".toLowerCase(), "ぇ");
        map2.put("LO".toLowerCase(), "ぉ");
        ABA("L");
        map3.put("LTU".toLowerCase(), "っ");
        map4.put("LLTU".toLowerCase(), "っっ");
        map3.put("LYA".toLowerCase(), "ゃ");
        map4.put("LLYA".toLowerCase(), "っゃ");
        map3.put("LYU".toLowerCase(), "ゅ");
        map4.put("LLYU".toLowerCase(), "っゅ");
        map3.put("LYO".toLowerCase(), "ょ");
        map4.put("LLYO".toLowerCase(), "っょ");
    }
    static String AAB(String str)
    {
        for(Map.Entry<String, String> e : map4.entrySet())
        {
            str = str.replace(e.getKey(), e.getValue());
        }
        for(Map.Entry<String, String> e : map3.entrySet())
        {
            str = str.replace(e.getKey(), e.getValue());
        }
        for(Map.Entry<String, String> e : map2.entrySet())
        {
            str = str.replace(e.getKey(), e.getValue());
        }
        for(Map.Entry<String, String> e : map1.entrySet())
        {
            str = str.replace(e.getKey(), e.getValue());
        }
        str = AAE.matcher(str).replaceAll("ん$1");
        return str;
    }
}
