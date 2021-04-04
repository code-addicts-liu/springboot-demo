package com.unicom.skyark.component.util;

import com.alibaba.fastjson.JSONArray;
import com.unicom.skyark.component.common.constants.SysTypes;
import com.unicom.skyark.component.data.DataMap;
import com.unicom.skyark.component.data.DatasetList;
import com.unicom.skyark.component.data.IData;
import com.unicom.skyark.component.data.IDataset;
import com.unicom.skyark.component.exception.SkyArkException;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zwqsmd on 2019/1/24.
 * 数据操作类
 */
public class addUtil {

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double addForDou(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static String IDatasetToString(IDataset datas) throws Exception {
        IData data = datas.toData();
        String names[] = data.getNames();
        String name = null;
        String returnstring = "";
        String tempstring = "";
        for (int j = 0; j < names.length; j++) {
            name = names[j];
            tempstring = name + "=";
            ArrayList al = (ArrayList) data.get(name);
            for (Iterator iterator = al.iterator(); iterator.hasNext(); ) {
                String object = (String) iterator.next();
                tempstring += object + ",";
            }
            tempstring = tempstring.substring(0, tempstring.length() - 1);
            returnstring += tempstring + ";";
        }
        return returnstring;
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static String addForDou(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被颊她
     * @param v2 颊她
     * @return 两个参数的差
     */
    public static double subForDou(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double subForDou(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mulForDou(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double mulForDou(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当放生除不尽的情况时，精确到小数点以后10位，以后的四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个除数的商
     */
    public static double divForDou(double v1, double v2) {
        return divForDou(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static double divForDou(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入
     *
     * @param v     需要四舍五入的数位
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 从IDataset里面找key对应的内容，如果和value相同，就返回目前这个IData
     *
     * @param datas
     * @param key
     * @param value
     * @return
     * @author chenjw
     */
    public static IData getTheData(IDataset datas, String key, String value) {
        for (Iterator iter = datas.iterator(); iter.hasNext(); ) {
            IData data = (IData) iter.next();
            if (data.containsKey(key) && data.getString(key).equals(value)) {
                return data;
            }
        }
        return null;
    }

    public static boolean ifContain(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从IDataset里面找key对应的内容，如果和value相同，就返回包涵这个IData的新的IDataset
     *
     * @param datas
     * @param key
     * @param value
     * @return
     * @author lif
     */
    public static IDataset getTheDataset(IDataset datas, String key, String value) {
        IDataset dataset = new DatasetList();
        for (Iterator iter = datas.iterator(); iter.hasNext(); ) {
            IData data = (IData) iter.next();
            if (data.containsKey(key) && data.getString(key).equals(value)) {
                dataset.add(data);
            }
        }
        return dataset;
    }

    /**
     * 从IDataset里面找key对应的内容，如果和value相同，就返回目前这个IData里面 column对应的那列值
     *
     * @param datas
     * @param key
     * @param value
     * @param column
     * @return
     */
    public static String getTheDataValue(IDataset datas, String key, String value, String column) {

        IData data = getTheData(datas, key, value);
        return data == null ? "" : data.getString(column);
    }


    /**
     * 从datas里面取出指定列的数据，形成一个IDataset，如果没有该值，就是用默认值
     *
     * @param datas
     * @param keys
     * @return
     * @throws Exception
     * @author chenjw
     */
    public static IDataset spliceIDataset(IDataset datas, String[] keys, String defaultValue) throws Exception {
        IDataset outParams = new DatasetList();
        for (Iterator iter = datas.iterator(); iter.hasNext(); ) {
            IData data = (IData) iter.next();
            IData outParam = new DataMap();
            for (int i = 0; i < keys.length; i++) {
                DataHelper.setParam(outParam, keys[i], data, defaultValue);
            }
            outParams.add(outParam);
        }
        return outParams;
    }

    /**
     * 从datas里面，把key对应的内容为null的那几行记录删除
     *
     * @param datas
     * @param key
     */
    public static void removeBlankObj(IDataset datas, String key) {
        for (int i = 0; i < datas.count(); i++) {
            IData data = (IData) datas.get(i);
            if (!data.containsKey(key) || data.getString(key) == null) {
                datas.remove(data);
            }
        }
    }

    /**
     * 从datas里面取出指定列的数据和要进行拼串的列的数据，形成指定的列名，形成一个IDataset
     *
     * @param datas
     * @param keys        指定要拼串的列
     * @param coloumns    指定的列
     * @param coloumnname 指定的列名
     * @return
     * @throws Exception
     */

    public static IDataset addIData(IDataset datas, String[] keys, String[] coloumns, String coloumnname) throws Exception {
        IDataset outParams = new DatasetList();

        for (Iterator iter = datas.iterator(); iter.hasNext(); ) {
            String descdata = "";
            IData data = (IData) iter.next();
            IData outParam = new DataMap();
            for (int i = 0; i < keys.length; i++) {
                descdata = descdata + data.getString(keys[i]) + " ";
            }
            outParam.put(coloumnname, descdata.substring(0, descdata.length() - 1));

            for (int j = 0; j < coloumns.length; j++) {
                DataHelper.setParam(outParam, coloumns[j], data);

            }
            outParams.add(outParam);
        }
        return outParams;
    }

    /**
     * 从IDataset里面找出所有与key对应的内容并且和value相同，形成一个IDataset
     *
     * @param datas
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public static IDataset spliceIData(IDataset datas, String key, String value) throws Exception {
        IDataset outParams = new DatasetList();
        for (Iterator iter = datas.iterator(); iter.hasNext(); ) {
            IData data = (IData) iter.next();
            IData outParam = new DataMap();
            if (data.getString(key).equals(value)) {
                outParam.putAll(data);
                outParams.add(outParam);
            }

        }
        return outParams;
    }

    /**
     * 把IDataset src1，src2,src3按照相关联的字段形成一定格式的String
     * [{value1=,value2=,value3=},{
     * value1=,value2=,value3=},{value1=,value2=,value3=}]
     *
     * @param src1
     * @param src2
     * @param src3
     * @param key1
     * @param key2
     * @return
     * @throws Exception
     */
    public static String FormatArray(IDataset src1, IDataset src2, IDataset src3, String key1, String key2) throws Exception {
        IDataset outParams = new DatasetList();
        String descdata1 = "";
        String descdata2 = "";
        for (Iterator iter1 = src1.iterator(); iter1.hasNext(); ) {
            IData data1 = (IData) iter1.next();
            descdata1 = data1.getString(key1);
            IDataset outParams2 = DataHelper.spliceIData(src2, key1, descdata1);
            if (outParams2.isEmpty()) {
                IData outParam = new DataMap();
                DataHelper.setParam(outParam, "value1", data1);
                outParams.add(outParam);
            }
            for (Iterator iter2 = outParams2.iterator(); iter2.hasNext(); ) {
                IData data2 = (IData) iter2.next();
                descdata2 = data2.getString(key2);
                IDataset outParams3 = DataHelper.spliceIData(src3, key2, descdata2);
                if (outParams3.isEmpty()) {
                    IData outParam = new DataMap();
                    DataHelper.setParam(outParam, "value1", data1);
                    DataHelper.setParam(outParam, "value2", data2);
                    outParams.add(outParam);
                }
                for (Iterator iter3 = outParams3.iterator(); iter3.hasNext(); ) {
                    IData data3 = (IData) iter3.next();
                    IData outParam = new DataMap();
                    DataHelper.setParam(outParam, "value1", data1);
                    DataHelper.setParam(outParam, "value2", data2);
                    DataHelper.setParam(outParam, "value3", data3);
                    // //System.out.println("cccc"+outParam);
                    outParams.add(outParam);
                    // //System.out.println("dddd"+outParams);
                }

            }
        }
        outParams.sort("value1", IDataset.TYPE_STRING, IDataset.ORDER_ASCEND, "value2", IDataset.TYPE_STRING, IDataset.ORDER_ASCEND);

        JSONArray arr = new JSONArray(outParams);
        return arr.toString();
    }

    /**
     * 判断是否为空
     *
     * @param srcData
     * @param key
     * @return
     */
    public static boolean isBlank(IData srcData, String key) {
        if (!srcData.containsKey(key) || srcData.getString(key) == null || srcData.getString(key).trim().equals("") || srcData.getString(key).trim().equals("-----请选择-----")) {
            return true;
        }
        return false;
    }

    /**
     * 如果srcData里面，没有key，或空字符串,那么直接抛出异常
     *
     * @param srcData
     * @param key
     * @author chenjuewei
     */
    public static void mustHave(IData srcData, String key) {
        if (isBlank(srcData, key)) {
            throw new SkyArkException(SysTypes.SYS_ERROR_CODE, key + "必须输入!");
        }
    }

    /**
     * 如果字符串为null，或空字符串，直接抛出异常
     *
     * @param s
     * @author chenjuewei
     */
    public static void mustHave(String s) {
        if (StringUtils.isEmpty(s)) {
            throw new SkyArkException(SysTypes.SYS_ERROR_CODE, s + "不能为空!");
        }
    }

    /**
     * 如果目标IData里面没有该键值或为null或为"",则返回默认值
     *
     * @param srcData
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static String getStr(IData srcData, String key, String defaultValue) {

        if (!srcData.containsKey(key) || srcData.getString(key) == null || srcData.getString(key).trim().equals("")) {
            return defaultValue;
        } else {
            return srcData.getString(key);
        }
    }

    /**
     * 如果目标IData里面没有该键值或为null或为"",则抛出异常
     *
     * @param srcData
     * @param key
     * @return
     * @throws Exception
     */
    public static String getStr(IData srcData, String key) {

        if (!srcData.containsKey(key) || srcData.getString(key) == null) {
            throw new SkyArkException(SysTypes.SYS_ERROR_CODE, key + "必须输入!");
        } else {
            return srcData.getString(key);
        }
    }


    /**
     * 获取字符串
     *
     * @param value
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static String getStr(String value, String defaultValue) {

        if (StringUtil.isEmptyCheckNullStr(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 如果目标IData里面没有该键值或为null或为"",则返回默认值
     *
     * @param srcData
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static int getInt(IData srcData, String key, String defaultValue) throws Exception {

        return toInt(getStr(srcData, key, defaultValue));
    }

    /**
     * 如果目标IData里面没有该键值或为null或为"",则抛出异常
     *
     * @param srcData
     * @param key
     * @return
     * @throws Exception
     */
    public static int getInt(IData srcData, String key) throws Exception {
        return toInt(getStr(srcData, key));
    }

    /**
     * 如果目标IData里面没有该键值或为null或为"",则返回默认值
     *
     * @param srcData
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public static double getDouble(IData srcData, String key, String defaultValue) throws Exception {

        return toDouble(getStr(srcData, key, defaultValue));
    }

    /**
     * 如果目标IData里面没有该键值或为null或为"",则抛出异常
     *
     * @param srcData
     * @param key
     * @return
     * @throws Exception
     */
    public static double getDouble(IData srcData, String key) throws Exception {
        return toDouble(getStr(srcData, key));
    }

    /**
     * 如果目标IData里面没有该键值或为null或为"",则设默认值
     *
     * @param descParam
     * @param key
     * @param defaultValue
     * @throws Exception
     */
    public static void setParam(IData descParam, String key, String defaultValue) throws Exception {

        if (!descParam.containsKey(key) || descParam.getString(key) == null || descParam.getString(key).trim().equals("")) {
            descParam.put(key, defaultValue.trim());
        }
    }

    /**
     * 将srcData中提取本业务相关的参数,没有则使用默认值
     *
     * @param descParam
     * @param key
     * @param srcData
     * @param defaultValue
     * @throws Exception
     */
    public static void setParam(IData descParam, String key, IData srcData, String defaultValue) throws Exception {
        if (srcData.containsKey(key) && srcData.getString(key) != null && !srcData.getString(key).trim().equals("")) {
            descParam.put(key, srcData.get(key));
        } else {
            descParam.put(key, defaultValue);
        }
    }

    /**
     * 将srcData中提取本业务相关的参数,没有则使用默认值(!针对数值性)
     *
     * @param descParam
     * @param key
     * @param srcData
     * @param defaultValue 该值不作处理
     * @param multiple     0.01除以100,100乘以100
     * @throws Exception
     */
    public static void setParam(IData descParam, String key, IData srcData, String defaultValue, double multiple) throws Exception {
        if (srcData.containsKey(key) && srcData.getString(key) != null && !srcData.getString(key).trim().equals("")) {
            descParam.put(key, toStr(toDouble(srcData.get(key)) * multiple));
        } else {
            descParam.put(key, defaultValue);
        }
    }

    /**
     * 将srcData中提取本业务相关的参数,没有则不设置(!针对数值性)
     *
     * @param descParam
     * @param key
     * @param srcData
     * @throws Exception
     */
    public static void setParam(IData descParam, String key, IData srcData) throws Exception {
        if (srcData.containsKey(key)) {
            descParam.put(key, srcData.get(key));
        }
    }

    /**
     * 将srcData中提取本业务相关的参数,没有则不设置
     *
     * @param descParam
     * @param key
     * @param srcData
     * @param multiple  0.01除以100,100乘以100
     * @throws Exception
     */
    public static void setParam(IData descParam, String key, IData srcData, double multiple) throws Exception {
        if (srcData.containsKey(key)) {
            descParam.put(key, toStr(toDouble(srcData.get(key)) * multiple));
        }
    }

    /**
     * 将srcData中提取本业务相关的参数,没有则抛出异常
     *
     * @param descParam
     * @param key
     * @param srcData
     * @param multiple  0.01除以100,100乘以100
     * @throws Exception
     */
    public static void setParamEx(IData descParam, String key, IData srcData, double multiple) throws Exception {
        if (srcData.containsKey(key)) {
            setParam(descParam, key, srcData, multiple);
        } else {
            throw new Exception(key.toString() + "必须传入！");
        }
    }

    /**
     * 将srcData中提取本业务相关的参数,没有则抛出异常
     *
     * @param srcData
     * @param key
     * @param srcData
     * @throws Exception
     */
    public static void setParamEx(IData descParam, String key, IData srcData) throws Exception {
        if (srcData.containsKey(key)) {
            descParam.put(key, srcData.get(key));
        } else {
            throw new Exception(key.toString() + "必须传入！");
        }
    }

    public static String div100(double value) {
        return toStr(value / 100);
    }

    public static String div1000(double value) {
        return toStr2(value / 1000);
    }

    public static String div100(String value) {
        return value == null ? "0.00" : toFormat(div100(toDouble(value)));
    }

    public static String div1000(String value) {
        return value == null ? "0.000" : toFormat2(div1000(toDouble(value)));
    }

    /**
     * 对ds中指定的若干列进行除100的金额转换
     *
     * @param ds
     * @param columns
     * @author skywalker
     */
    public static void div100Dataset(IDataset ds, String[] columns) {
        for (int i = 0; i < ds.size(); i++) {
            IData tmpData = (IData) ds.get(i);
            for (int j = 0; j < columns.length; j++) {
                tmpData.put(columns[j], div100(tmpData.getString(columns[j])));
            }
        }
    }

    public static void div1000Dataset(IDataset ds, String[] columns) {
        for (int i = 0; i < ds.size(); i++) {
            IData tmpData = (IData) ds.get(i);
            for (int j = 0; j < columns.length; j++) {
                tmpData.put(columns[j], div1000(tmpData.getString(columns[j])));
            }
        }
    }

    /**
     * 添加一列序号列
     *
     * @param ds
     * @param snname
     */
    public static void addSN(IDataset ds, String snname) {
        for (int i = 0; i < ds.size(); i++) {
            IData tmpData = (IData) ds.get(i);
            tmpData.put(snname, i + 1 + "");
        }
    }

    /**
     * 根据列取和
     *
     * @param ds
     * @param column
     */
    public static String getSumCol(IDataset ds, String column) {

        String fee = "0";
        for (int i = 0; i < ds.size(); i++) {

            IData tmpData = (IData) ds.get(i);

            fee = addForDou(fee, tmpData.get(column).toString());
        }
        return fee;
    }

    /**
     * 根据列名 添加一行合计列
     *
     * @param ds
     * @param columns
     * @param col     这个2个是写中文 合计 2字用的
     * @param name
     */
    public static void addSumCol(IDataset ds, String[] columns, String col, String name) {
        IData tmpData2 = new DataMap();
        tmpData2.put(col, name);
        for (int i = 0; i < columns.length; i++) {

            String fee = "0";
            for (int j = 0; j < ds.size(); j++) {

                IData tmpData = (IData) ds.get(j);

                fee = addForDou(fee, tmpData.get(columns[i]).toString());
            }
            tmpData2.put(columns[i], fee);
        }
        ds.add(tmpData2);
    }

    public static String mult100(double value) {

        BigDecimal b1 = new BigDecimal(value + "");
        BigDecimal b2 = new BigDecimal("100");
        BigDecimal r = b1.multiply(b2);
        String result = r.toString();
        if (result.indexOf(".") != -1) {
            return result.substring(0, result.indexOf("."));
        }
        return result;
    }

    public static String mult100(String value) {
        if (value == null || value.equals("")) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(value);
        BigDecimal b2 = new BigDecimal("100");
        BigDecimal r = b1.multiply(b2);
        String result = r.toString();
        if (result.indexOf(".") != -1) {
            return result.substring(0, result.indexOf("."));
        }
        return result;
    }

    /**
     * 对ds中指定的若干列进行乘100的金额转换
     *
     * @param ds
     * @param columns
     * @author skywalker
     */
    public static void mult100Dataset(IDataset ds, String[] columns) {
        for (int i = 0; i < ds.size(); i++) {
            IData tmpData = (IData) ds.get(i);
            for (int j = 0; j < columns.length; j++) {
                tmpData.put(columns[j], mult100(tmpData.getString(columns[j])));
            }
        }
    }

    // 将data里面指定的值除以100
    public static void div100(IData data, String key) {
        if (key == null || key.equals("")) {
            key = "0";
        }
        data.put(key, div100(data.getString(key)));
    }

    /**
     * 在data里面的某个内容后面加上个 String。 例如 data里面有个值是 cjw,
     * 我们在它后面加上个'123',就可以用这个了，变为cjw123
     *
     * @param data
     * @param key
     * @param c
     * @author chenjw
     */
    public static void addString(IData data, String key, String c) {
        if (key == null || key.equals("")) {
            throw new SkyArkException(SysTypes.SYS_ERROR_CODE, "addString() 时, key为 null 或 ''");
        }
        if (c == null || c.equals("")) {
            return;
        }
        data.put(key, data.getString(key) + c);
    }

    // 将data里面指定的值乘以100
    public static void mult100(IData data, String key) {
        String s = data.getString(key);
        if (StringUtils.isBlank(s)) {
            data.put(key, "0");
        } else {
            data.put(key, mult100(s));
        }
    }

    // public static void copy(IData desc, IData src, String parrern) {
    // String[] pairs = parrern.split(",");
    // for (int i = 0; i < pairs.length; ++i) {
    // String pair = pairs[i];
    // String[] titlePair = pair.split("\\|");
    // String title = titlePair.length > 0 ? titlePair[0] : null;
    // String type = titlePair.length > 1 ? titlePair[1] : null;
    // desc.put(title, src.get(title));
    // }
    // }

    // String 转化成 int
    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }

        String value = obj.toString();
        value = value.trim();
        if (value == null || value.length() == 0) {
            return 0;
        } else {
            return (Integer.valueOf(value)).intValue();
        }
    }

    // String 转化成 double 并进行格式化
    public static double toDouble(Object obj) {
        String value;
        if (obj == null) {
            value = "0.00";
        } else {
            value = obj.toString().trim();
        }
        if (value == null || value.length() == 0) {
            return 0.00;
        } else {
            return (Double.valueOf(value)).doubleValue();
        }

    }

    // int 转化成 String
    public static String toStr(int value) {
        return Integer.toString(value);
    }

    // double 转化成 String 并格式化成0.00
    public static String toStr(double value) {
        return toFormat(Double.toString(value));
    }

    public static String toStr2(double value) {
        return toFormat2(Double.toString(value));
    }

    // double 转化成 String 根据需要根是化格式化成0.00
    public static String toStr(double value, boolean bFormat) {
        if (bFormat) {
            return toStr(value);
        } else {
            return Double.toString(value);
        }
    }

    // 数字的String 格式化0.00后输出
    public static String toFormat(Object obj) {
        double value = toDouble(obj);
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(value);
    }

    public static String toFormat2(Object obj) {
        double value = toDouble(obj);
        DecimalFormat format = new DecimalFormat("0.000");
        return format.format(value);
    }

    // String 转化成 long 并进行格式化
    public static long toLong(Object obj) {
        String value;
        if (obj == null) {
            value = "0";
        } else {
            value = obj.toString().trim();
        }
        if (value == null || value.length() == 0) {
            return 0;
        } else {
            return (Long.valueOf(value)).longValue();
        }
    }
/*
    public static String strToList(String value) {
        List list = null;
        if (value == null)
            return null;
        Pattern pattern = Pattern.compile("([\\[|\\{]+\")");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find() || !value.startsWith(matcher.group())) {
            StringBuffer str = new StringBuffer();
            pattern = Pattern.compile("(\\r)|(\\n)|(\\$)|(\", \")|(\\[?\"\\]?)");
            matcher = pattern.matcher(value);
            do {
                if (!matcher.find())
                    break;
                String group = matcher.group();
                if ("\r".equals(group))
                    matcher.appendReplacement(str, "!~5~!");
                else if ("\n".equals(group))
                    matcher.appendReplacement(str, "!~6~!");
                else if ("$".equals(group))
                    matcher.appendReplacement(str, "!~7~!");
                else if ("\"".equals(group))
                    matcher.appendReplacement(str, "!~8~!");
            } while (true);
            matcher.appendTail(str);
            value = str.toString();
            str = new StringBuffer();
            pattern = Pattern.compile("(\".*?\")");
            StringBuffer substr;
            for (matcher = pattern.matcher(value); matcher.find(); matcher.appendReplacement(str, substr.toString())) {
                String group = matcher.group();
                substr = new StringBuffer();
                Pattern subpattern = Pattern.compile("(\\{)|(\\[)|(\\])|(,)");
                Matcher submatcher = subpattern.matcher(group);
                do {
                    if (!submatcher.find())
                        break;
                    String subgroup = submatcher.group();
                    if ("{".equals(subgroup))
                        submatcher.appendReplacement(substr, "!~1~!");
                    else if ("[".equals(subgroup))
                        submatcher.appendReplacement(substr, "!~2~!");
                    else if ("]".equals(subgroup))
                        submatcher.appendReplacement(substr, "!~3~!");
                    else if (",".equals(subgroup))
                        submatcher.appendReplacement(substr, "!~4~!");
                } while (true);
                submatcher.appendTail(substr);
            }

            matcher.appendTail(str);
            value = str.toString();
            str = new StringBuffer();
            pattern = Pattern.compile("(=?[\\{\\[][\\{\\}\\[\\]]*(, )?[\\{\\}\\[]*)|([\\}\\]]*, [\\{\\[]*)|(\", \")");
            String group;
            for (matcher = pattern.matcher(value); matcher.find(); matcher.appendReplacement(str, group)) {
                group = matcher.group();
                if (group.startsWith("="))
                    group = "\":" + group.substring(1);
                if (group.endsWith("{"))
                    group = group + "\"";
                else if (group.endsWith(" "))
                    group = group + "\"";
                group = group.replaceFirst(" ", "");
            }

            matcher.appendTail(str);
            value = str.toString();
            str = new StringBuffer();
            pattern = Pattern.compile("(\\[\".*?\"\\])");
            for (matcher = pattern.matcher(value); matcher.find(); ) {
                group = matcher.group();
                if (Pattern.compile("(\",\")").matcher(group).find())
                    matcher.appendReplacement(str, group);
                else
                    matcher.appendReplacement(str, group.substring(1, group.length() - 1));
            }

            matcher.appendTail(str);
            value = str.toString();
            str = new StringBuffer();
            pattern = Pattern.compile("(!~1~!)|(!~2~!)|(!~3~!)|(!~4~!)|(!~5~!)|(!~6~!)|(!~7~!)|(!~8~!)");
            matcher = pattern.matcher(value);
            do {
                if (!matcher.find())
                    break;
                group = matcher.group();
                if ("!~1~!".equals(group))
                    matcher.appendReplacement(str, "{");
                else if ("!~2~!".equals(group))
                    matcher.appendReplacement(str, "[");
                else if ("!~3~!".equals(group))
                    matcher.appendReplacement(str, "]");
                else if ("!~4~!".equals(group))
                    matcher.appendReplacement(str, ",");
                else if ("!~5~!".equals(group))
                    matcher.appendReplacement(str, "\\\\r");
                else if ("!~6~!".equals(group))
                    matcher.appendReplacement(str, "\\\\n");
                else if ("!~7~!".equals(group))
                    matcher.appendReplacement(str, "\\$");
                else if ("!~8~!".equals(group))
                    matcher.appendReplacement(str, "\\\\\"");
            } while (true);
            matcher.appendTail(str);
            value = str.toString();
        }
        if (!value.startsWith("[") || !value.endsWith("]"))
            value = "[" + value + "]";

        return value;
    }*/


    public static String formatDateEss(String time) throws Exception {
        if (time == null) {
            return "";
        }
        time = time.concat("00000000000000000".substring(time.length()));
        StringBuffer sbf = new StringBuffer();
        sbf.append(time.substring(0, 4));
        sbf.append("-");
        sbf.append(time.substring(4, 6));
        sbf.append("-");
        sbf.append(time.substring(6, 8));
        sbf.append(" ");
        sbf.append(time.substring(8, 10));
        sbf.append(":");
        sbf.append(time.substring(10, 12));
        sbf.append(":");
        sbf.append(time.substring(12, 14));
        return sbf.toString();
    }

    /**
     * 判断是数字
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static boolean isNumber(String str) throws Exception {
        char[] cStr = str.toCharArray();
        for (int i = 0; i < cStr.length; i++) {
            if (cStr[i] >= '0' && cStr[i] <= '9') {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
