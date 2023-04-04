package iot.cloud.platform.lock.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iot.cloud.platform.lock.service.PwdService;
import iot.cloud.platform.lock.utils.DateUtil;
import iot.cloud.platform.lock.utils.ExcptUtil;
import iot.cloud.platform.lock.vo.MqttMsg;
import iot.cloud.platform.lock.vo.ResMsg;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * mqtt回调处理类
 */

@Component
@Slf4j
public class MqttConsumerCallback implements MqttCallbackExtended {

    @Autowired
    private MqttClient client;
    @Autowired
    private MqttConnectOptions options;
    @Autowired
    @Qualifier("mqttSubTopics")
    private Map<String, Integer> mqttSubTopics;

    @Autowired
    @Qualifier("mqttTopicReplyMap")
    private Map<String, String> mqttTopicReplyMap;

    @Autowired
    private PwdService pwdService;

    /**
     * 断开重连
     */
    @Override
    public void connectionLost(Throwable cause) {
        log.info("MQTT连接断开", cause);
        try {
            if (null != client && !client.isConnected()) {
                client.reconnect();
                log.info("尝试重新连接");
            } else {
                client.connect(options);
                log.info("尝试建立新连接");
            }
        } catch (Exception e) {
            log.error(ExcptUtil.filterStack(e));
        }
    }

    /**
     * 消息处理
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            String msg = new String(message.getPayload());
            log.info("收到主题[" + topic + "]消息 ->" + msg);
            ObjectMapper objMapper = new ObjectMapper();
            MqttMsg<HashMap> resvMsg = null;
            try {
                //ObjectMapper 是 Jackson包用于解析 Json 为java 对象
                resvMsg = objMapper.readValue(msg, MqttMsg.class);
            } catch (JsonProcessingException e) {
                log.error(ExcptUtil.filterStack(e));
            }

            //TODO:请完善此处代码，完成实验6.3,6.4的功能。
//            ResMsg returnVal = null;
            ResMsg returnVal = new ResMsg();
            String pwd = resvMsg.getData().get("oPwd").toString();
            if (pwdService.resetFixedPwd(pwd)) {
                if (pwdService.verifyPwd(pwd)) {
                    returnVal.setErrcode("0");
                    returnVal.setErrmsg("更新密码成功");
                    returnVal.setData(pwd);
                } else {
                    returnVal.setErrcode("4002");
                    returnVal.setErrmsg("更新密码失败，新密码格式错误");
                    returnVal.setData(pwd);
                }
            } else {
                returnVal.setErrcode("4001");
                returnVal.setErrmsg("更新密码失败，旧密码错误");
                returnVal.setData(pwd);
            }


            MqttMsg<ResMsg> replyMsg = new MqttMsg();
            replyMsg.setEventId(resvMsg.getEventId());
            replyMsg.setEventName(resvMsg.getEventName());
            replyMsg.setEventTime(resvMsg.getEventTime());
            replyMsg.setData(returnVal);
            String receiveReplyMsg = "{}";
            try {
                //把replyMsg对象转换为 JSON 消息
                receiveReplyMsg = objMapper.writeValueAsString(replyMsg);
                log.info("回复MQTT消息：" + receiveReplyMsg);
                String replyTopic = mqttTopicReplyMap.get(topic);
                if (replyTopic != null) {
                    client.publish(replyTopic, receiveReplyMsg.getBytes(StandardCharsets.UTF_8), 1, true);
                }
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            log.error(ExcptUtil.filterStack(e));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    @Override
    public void connectComplete(boolean b, String s) {
        mqttSubTopics.forEach((k, v) -> {
            try {
                client.subscribe(k, v);
                log.info("订阅主题:" + k);
            } catch (MqttException e) {
                log.error(ExcptUtil.filterStack(e));
            }
        });
    }
}