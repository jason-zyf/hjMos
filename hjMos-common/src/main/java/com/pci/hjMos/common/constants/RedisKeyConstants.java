package com.pci.hjMos.common.constants;

/**
 * TODO
 *
 * @author lsk
 * @class_name TokenConstants
 * @date 2019-05-30
 */
public class RedisKeyConstants {


    public final static String PREFIX_IMC = "imc:";

    public final static String PREFIX_STREAMING = "imc:streaming:";

    public final static String PREFIX_OCC = "OCC";

    public final static String PREFIX_ZHC = "ZHC";

    public final static String PREFIX_SFZ = "SFZ";

    /**
     * redis-key-前缀-pci:imc:data_hub:access_token:
     */
    public final static String PREFIX_SECURITY_ACCESS_TOKEN = "imc:datahub:access_token:";

    public final static String PREFIX_ALARM_POINT = "imc:datahub:alm:";

    public final static String PREFIX_ALL_ALARM_POINT = "imc:datahub:alm:all:";

    public final static String PREFIX_ALIVE_TODAY_ALARM_POINT_LIST = "imc:datahub:alm:alive:today";

    public final static String PREFIX_ALIVE_HISTORY_ALARM_POINT_LIST = "imc:datahub:alm:alive:history";

    public final static String PREFIX_SCENES_PERSONAL_PKEY = "imc:datahub:scenes:personal:";

    public final static String PREFIX_AUTH_DEVICE_PKEY = "imc:datahub:auth:device";

    public final static String PREFIX_SCENES_CURRENT_PKEY = "imc:datahub:scenes:current";

    public final static String PREFIX_GLOBAL_MESSAGE_PKEY = "imc:datahub:global:message:";


    /**
     * AFC客流缓存前缀
     */
    public final static String PREFIX_AFC_PFLOW = "imc:streaming:afc";
    /**
     * AFC客流概况 (string)
     * Data: {@link com.pci.imc.common.entity.AfcPflowStat}
     */
    public final static String PREFIX_AFC_PFLOW_STAT = "imc:streaming:afcPflowStat:";
    /**
     * AFC高峰客流数据
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PFLOW_PEAK = "imc:streaming:afcPflowPeak:";
    /**
     * AFC进站客流 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PFLOW_IN_LIST = "imc:streaming:afcPflowIn:";
    /**
     * AFC出站客流 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PFLOW_OUT_LIST = "imc:streaming:afcPflowOut:";
    /**
     * AFC乘降量 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PFLOW_ALL_LIST = "imc:streaming:afcPflowAll:";
    /**
     * 15分钟的AFC进站客流 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PFLOW_QUARTER_IN_LIST = "imc:streaming:afcPflowInQuarter:";
    /**
     * 15分钟的AFC出站客流 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PFLOW_QUARTER_OUT_LIST = "imc:streaming:afcPflowOutQuarter:";
    /**
     * 15分钟的AFC乘降量 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PFLOW_QUARTER_ALL_LIST = "imc:streaming:afcPflowAllQuarter:";
    /**
     * AFC预测进站客流 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PRE_PFLOW_IN_LIST = "imc:streaming:afcPrePflowIn:";
    /**
     * AFC预测出站客流 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PRE_PFLOW_OUT_LIST = "imc:streaming:afcPrePflowOut:";
    /**
     * AFC预测乘降量 (list) key 为  prefix + stationId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_AFC_PRE_PFLOW_ALL_LIST = "imc:streaming:afcPrePflowAll:";
    /**
     * AFC闸机累计客流 (hash) key 为  prefix + stationId, hashKey 为 deviceId
     * Data: {@link com.pci.imc.common.entity.AfcGateFlowStat}
     */
    public final static String PREFIX_AFC_GATE_TOTAL_FLOW = "imc:streaming:afcGateTotalFlow:";
    /**
     * AFC闸机实时客流 (hash) key 为  prefix + stationId, hashKey 为 deviceId
     * Data: {@link com.pci.imc.common.entity.AfcGateFlowStat}
     */
    public final static String PREFIX_AFC_GATE_RT_FLOW = "imc:streaming:afcGateRtFlow:";
    /**
     * CCTV摄像机实时总客流量 (hash) key 为 prefix + stationId, hashKey 为 cameraId
     * Data: {@link com.pci.imc.streaming.mq.message.CctvFlowMessage}
     */
    public final static String PREFIX_CCTV_CAMERA_TOTALFLOW_RT_HASH = "imc:streaming:cctvCameraTotalFlowRt:";
    /**
     * CCTV摄像机客流量 (hash) key 为 prefix + stationId, hashKey 为 cameraId
     * Data: {@link com.pci.imc.common.entity.CctvPflowInfo}
     */
    public final static String PREFIX_CCTV_CAMERA_FLOW_RT_HASH = "imc:streaming:cctvCameraFlowRt:";
    /**
     * CCTV摄像机人员密度 (hash) key 为 prefix + stationId, hashKey 为 cameraId
     * Data: {@link com.pci.imc.common.entity.CameraFlowInfo}
     */
    public final static String PREFIX_CCTV_CAMERA_PDENSITY_RT_HASH = "imc:streaming:cctvCameraPdensityRt:";
    /**
     * CCTV区域客流密度 (hash) key 为 prefix + stationId , hashKey 为 regionId
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_CCTV_PDENSITY_RT_HASH = "imc:streaming:cctvPdensityRt:";
    /**
     * CCTV重点区域客流曲线 (list) key 为 prefix + regionId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_CCTV_PDENSITY_HIS_LIST = "imc:streaming:cctvPdensityHis:";
    /**
     * CCTV重点区域客流预测曲线 (list) key 为 prefix + regionId + dateStr
     * Data: {@link com.pci.imc.common.entity.DataPoint}
     */
    public final static String PREFIX_CCTV_PDENSITY_PRE_LIST = "imc:streaming:cctvPdensityPre:";
    /**
     * CCTV重要区域拥挤度列表 (hash) key 为 prefix + stationId, hashKey 为 regionId
     * Data: {@link com.pci.imc.common.entity.CctvSaturation}
     */
    public final static String PREFIX_CCTV_PDENSITY_STAT_HASH = "imc:streaming:cctvPdensityStat:";
    /**
     * CCTV区域客流量信息表 (hash) key 为 prefix + stationId, hashKey 为 regionId
     * Data: {@link com.pci.imc.common.entity.CctvRegionPflowInfo}
     */
    public final static String PREFIX_CCTV_PFLOW_STAT_HASH = "imc:streaming:cctvPflowStat";
    /**
     * CCTV区域类型客流密度 (hash) key 为 prefix + stationId, hashKey 为 subRegionType
     * Data: {@link com.pci.imc.common.entity.CctvRegionTypeInfo}
     */
    public final static String PREFIX_CCTV_REGIONTYPE_STAT_HASH = "imc:streaming:cctvRegionTypeStat:";
    /**
     * CCTV车站总客流密度统计 (string) key 为 prefix + stationId
     * Data: {@link com.pci.imc.common.entity.CctvStationInfo}
     */
    public final static String PREFIX_CCTV_STATION_STAT_HASH = "imc:streaming:cctvStationStat:";
    /**
     * CCTV事件 (string) key 为 prefix + cameraCode
     * Data: {@link com.pci.imc.common.entity.CctvEventInfo}
     */
    public final static String PREFIX_CCTV_ALARM_HASH = "imc:streaming:cctvEvent:";
    /**
     * WIFI车站人数统计 (hash) key 为 prefix + stationId, hashKey 为 regionId
     * Data: {@link com.pci.imc.common.entity.WifiStationFlow}
     */
    public final static String PREFIX_WIFI_STATION_STAT_HASH = "imc:streaming:wifiStationStat:";

    /**
     * WIFI列车车厢统计 (hash) key 为 prefix + trainId , hashKey 为 车厢号
     * Data: {@link com.pci.imc.common.entity.WifiTrainFlow}
     */
    public final static String PREFIX_WIFI_TRAIN_STAT_HASH = "imc:streaming:wifiTrainStat:";

    /**
     * 视频质量当前状态 (hash) key 为 prefix + stationId , hashKey 为  cameraId
     */
    public final static String PREFIX_STREAM_QUALITY_STAT_HASH = "imc:streaming:cctvStreamQualityStat:";

    /**
     * 室内人员定位 (hash) key 为 prefix + stationId , hashKey 为  userId(用户ID)
     */
    public final static String PREFIX_LOCATE_PEOPLE_INDOOR_HASH = "imc:streaming:locatePeopleIndoor:";

    /**
     * 室内人员定位 (String) key 为 prefix + stationId
     */
    public final static String PREFIX_LOCATE_PEOPLE_INDOOR_STRING = "imc:streaming:locatePeopleIndoor:string:";

    /**
     * 安检接口设备当前状态 (hash) key 为 prefix + stationId, hashKey 为 deviceId
     * Data: {@link com.pci.imc.common.entity.SecurityCheckDeviceStat}
     */
    public final static String PREFIX_SECURITY_CHECK_DEV_STAT_HASH = "imc:streaming:securityCheckDevStat:";
    /**
     * 安检接口区域告警统计明细结果 (hash) key 为 prefix + stationId + dateStr, hashKey 为 regionId
     * Data: {@link com.pci.imc.common.entity.SecurityCheckRegionStat}
     */
    public final static String PREFIX_SECURITY_CHECK_REGION_STAT_HASH = "imc:streaming:securityCheckRegionStat:";
    /**
     * 安检接口区域告警统计总数结果 (hash) key 为 prefix + stationId + dateStr, hashkey 为 regionId
     * Data: {@link Integer}
     */
    public final static String PREFIX_SECURITY_CHECK_REGION_TOTAL_HASH = "imc:streaming:securityCheckRegionTotal:";
    /**
     * 安检接口设备实时在线状态 (hash) key 为 prefix + stationId, hashkey 为 deviceId
     * Data: {@link Integer}
     */
    public final static String PREFIX_SECURITY_CHECK_DEV_ON_OFF_HASH = "imc:streaming:securityCheckDevOnOff:";
    /**
     * 安检接口设备事件有序列表 (zset) key 为 prefix + stationId, score 为序列
     * Data: {@link com.pci.imc.common.entity.SecurityCheckDeviceStat}
     */
    public final static String PREFIX_SECURITY_CHECK_EVENT_ZSET = "imc:streaming:securityCheckEventZset:";
    /**
     * imc streaming score序列 (string) key 为 prefix + key
     * Data: {@link Long}
     */
    public final static String PREFIX_IMC_STREAMING_SEQ = "imc:streaming:sequence:";
    /**
     * AFC人脸闸机过闸识别信息 (zset) key 为 prefix + stationId, score 为序列
     * Data: {@link package com.pci.imc.common.entity.AfcFaceCapture}
     */
    public final static String PREFIX_AFC_FACE_CAPTURE_STRING = "imc:streaming:afcFaceCaptureString:";
    /**
     * 电扶梯告警明细清单 (hash) key 为 prefix + stationId + escId, hashKey 为 statusId, 值为EscalatorEvent
     */
    public final static String PREFIX_ESCALATOR_STAT_DETAIL = "imc:streaming:escalatorStatDetail:";
    /**
     * 电扶梯告警数量明细 (hash) key 为 prefix + stationId, hashkey 为 扶梯escId, 值为List
     */
    public final static String PREFIX_ESCALATOR_ALARM_COUNT_DETAIL = "imc:streaming:escalatorAlarmCountDetail:";
    /**
     * 电扶梯告警数量统计 (string) key 为 prefix + stationId, 值为List
     */
    public final static String PREFIX_ESCALATOR_ALARM_COUNT_TOTAL = "imc:streaming:escalatorAlarmCountTotal";

    /**
     * AFC人脸闸机过闸识别信息 (zset) key 为 prefix + stationId, score 为序列
     */
    public final static String PREFIX_AFC_FACE_CAPTURE_TOTAL_COUNT = "imc:streaming:afcFaceCaptureTotalCount:";
    /**
     * 列车车厢拥挤度1
     */
    public final static String PREFIX_TRAIN1_CROWDING_STRING = "OCC.SIG.PIDLocation.2106-01.eiiSIG-PIDLocationTrain1Crowding";
    /**
     * 列车车厢拥挤度2
     */
    public final static String PREFIX_TRAIN2_CROWDING_STRING = "OCC.SIG.PIDLocation.2106-02.eiiSIG-PIDLocationTrain1Crowding";
    /**
     * 列车上行到站信息key prefix
     */
    public final static String PREFIX_TRAIN_FORWARD_INFO = "OCC.SIG.PIDLocation.2106-01.";
    /**
     * 列车下行到站信息key prefix
     */
    public final static String PREFIX_TRAIN_REVERSE_INFO = "OCC.SIG.PIDLocation.2106-02.";
}
