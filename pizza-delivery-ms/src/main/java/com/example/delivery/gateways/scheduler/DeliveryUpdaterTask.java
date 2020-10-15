package com.example.delivery.gateways.scheduler;

import com.example.delivery.gateways.async.producers.DeliveryStatusUpdateProducer;
import com.example.delivery.persistence.dto.DeliveryDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

@Scope("prototype")
@Component
public class DeliveryUpdaterTask extends TimerTask {

    enum Status {
        ORDERED ,PREPARING_DELIVERY, IN_DELIVERY, DELIVERED;
    }

    protected DeliveryStatusUpdateProducer deliveryStatusUpdateProducer;
    protected DeliveryDTO deliveryDTO;
    protected List<Status> statusList;

    public DeliveryUpdaterTask(DeliveryStatusUpdateProducer deliveryStatusUpdateProducer) {
        this.deliveryStatusUpdateProducer = deliveryStatusUpdateProducer;
        this.statusList =  Arrays.asList(Status.values());
    }

    public void setDelivery(DeliveryDTO deliveryDTO) {
        this.deliveryDTO = deliveryDTO;
    }

    @Override
    public void run() {
        // update delivery
        Status newStatus = getNextStatus();
        deliveryDTO.setStatus(getStatusString(newStatus));
        deliveryDTO.setUpdatedTime(LocalDateTime.now());

        // produce to update database
        deliveryStatusUpdateProducer.produce(deliveryDTO);

        // stopping condition
        if (Status.DELIVERED.equals(newStatus)) {
            cancel();
        }
    }

    protected Status getNextStatus() {
        String currentStatus = deliveryDTO.getStatus();
        int indexOfNextStatus = statusList.indexOf(currentStatus) + 1;

        return statusList.get(indexOfNextStatus);
    }

    protected String getStatusString(Status status) {
        switch (status) {
            case ORDERED:
                return "Ordered";
            case PREPARING_DELIVERY:
                return "Preparing Delivery";
            case IN_DELIVERY:
                return "In Delivery";
            case DELIVERED:
                return "Delivered";
            default:
                return "Invalid";
        }
    }
}
