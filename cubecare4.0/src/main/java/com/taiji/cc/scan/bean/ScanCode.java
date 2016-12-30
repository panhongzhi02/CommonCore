package com.taiji.cc.scan.bean;

/**
 * 扫描功能模块涉及到的常量<br>
 * =========扫描类别Code===========<br>
 *     <table>
 *     <tr>
 *         <th>code码</th><th>含义</th>
 *     </tr>
 *     <tr>
 *         <td>1</td><td>患者腕带</td>
 *     <tr>
 *         <td>2</td><td>输液瓶签</td>
 *     </tr>
 *     <tr>
 *         <td>3</td><td>口服药条码</td>
 *     </tr>
 * </table>
 * =========扫描内容处理之后返回结果Code==========<br>
 * <table>
 *     <tr>
 *         <th>code码</th><th>含义</th>
 *     </tr>
 *     <tr>
 *         <td>200</td><td>扫描成功</td>
 *     </tr>
 *     <tr>
 *         <td>401</td><td>该病区不存在该患者</td>
 *     </tr>
 *     <tr>
 *         <td>402</td><td>无法识别类型</td>
 *     </tr>
 *     <tr>
 *         <td>403</td><td>未查到医嘱信息</td>
 *     </tr>
 *     <tr>
 *         <td>404</td><td>该医嘱已停止</td>
 *     </tr>
 *     <tr>
 *         <td>405</td><td>数据库未同步到医嘱</td>
 *     </tr>
 *     <tr>
 *         <td>406</td><td>未进行一次（摆液）核对！</td>
 *     </tr>
 *     <tr>
 *         <td>407</td><td>未进行二次（配液）核对！</td>
 *     </tr>
 *     <tr>
 *         <td>408</td><td>医嘱不在有效的核对范围！</td>
 *     </tr>
 *     <tr>
 *         <td>409</td><td>医嘱不在有效的执行范围！</td>
 *     </tr>
 * </table>
 * Created by panho on 2016/9/14.
 */
public class ScanCode{
    /**
     * 腕带
     */
    public static final int PATIENT_CODE = 1;
    /**
     * 输液瓶签
     */
    public static final int INFUSION_CODE = 2;
    /**
     * 口服药
     */
    public static final int MEDICINE_CODE = 3;
    /**
     * 无法识别类型
     */
    public static final int UNABLE_CODE = 4;

    /**
     * 该病区不存在该患者
     */
    public static final int PATIENT_NOT_EXIST_CODE = 401;
    public static final String PATIENT_NOT_EXIST_MESSAGE = "该病区不存在该患者！";
    /**
     * 无法识别扫描类型
     */
    public static final int UNABLE_TO_IDENTIFY_CODE = 402;
    public static final String UNABLE_TO_IDENTIFY_MESSAGE = "无法识别类型！";
    /**
     * HIS系统中不存在该医嘱
     */
    public static final int ORDER_NOT_EXIST_CODE = 403;
    public static final String ORDER_NOT_EXIST_MESSAGE = "未查到医嘱信息！";
    /**
     * HIS系统中该医嘱已停止
     */
    public static final int ORDER_STOP_CODE = 404;
    public static final String ORDER_STOP_MESSAGE = "该医嘱已停止!";
    /**
     * 移动护理数据库未同步到医嘱
     */
    public static final int ORDER_UNSYNC_CODE = 405;
    public static final String ORDER_UNSYNC_MESSAGE = "数据库未同步到医嘱！";
    /**
     * 未进行一次核对
     */
    public static final int NOT_ONE_CHECK_CODE = 406;
    public static final String NOT_ONE_CHECK_MESSAGE = "未进行一次（摆液）核对！";
    /**
     * 未进行二次核对
     */
    public static final int NOT_TWO_CHECK_CODE = 407;
    public static final String NOT_TWO_CHECK_MESSAGE = "未进行二次（配液）核对！";
    /**
     * 医嘱不在有效的核对时间范围
     */
    public static final int CHECK_OUTDATE_CODE = 408;
    public static final String CHECK_OUTDATE_MESSAGE = "医嘱不在有效的核对时间范围！";
    /**
     * 医嘱不在有效的执行时间范围
     */
    public static final int EXECUTE_OUTDATE_CODE = 409;
    public static final String EXECUTE_OUTDATE_MESSAGE = "医嘱不在有效的执行时间范围！";

    /**
     * 扫描并且处理成功
     */
    public static final int SCAN_SUCCESS = 200;
    public static final String SCAN_PATIENT_SUCCESS_MESSAGE = "扫描成功！";


}
