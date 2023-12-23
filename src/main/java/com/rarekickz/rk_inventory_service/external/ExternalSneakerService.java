package com.rarekickz.rk_inventory_service.external;

import com.google.protobuf.Empty;
import com.google.rpc.Code;
import com.google.rpc.Status;
import com.rarekickz.proto.lib.OrderTotalPriceResponse;
import com.rarekickz.proto.lib.ReserveSneakersRequest;
import com.rarekickz.proto.lib.SneakerIdsRequest;
import com.rarekickz.proto.lib.SneakerServiceGrpc;
import com.rarekickz.rk_inventory_service.dto.ReserveSneakerDTO;
import com.rarekickz.rk_inventory_service.exception.InvalidSizeException;
import com.rarekickz.rk_inventory_service.exception.InvalidSneakerException;
import com.rarekickz.rk_inventory_service.service.SneakerService;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

import static com.rarekickz.rk_inventory_service.external.converter.ExternalSneakerConverter.convertToReserveSneakerDTOs;

@GrpcService
@RequiredArgsConstructor
public class ExternalSneakerService extends SneakerServiceGrpc.SneakerServiceImplBase {

    private final SneakerService sneakerService;

    @Override
    public void reserve(final ReserveSneakersRequest request, final StreamObserver<Empty> responseObserver) {
        final List<ReserveSneakerDTO> sneakersToBeReserved = convertToReserveSneakerDTOs(request);
        try {
            sneakerService.reserve(sneakersToBeReserved);
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (InvalidSizeException ex) {
            final Status status = Status.newBuilder()
                    .setCode(Code.INVALID_ARGUMENT_VALUE)
                    .setMessage("The selected size is not available")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        } catch (InvalidSneakerException ex) {
            final Status status = Status.newBuilder()
                    .setCode(Code.INVALID_ARGUMENT_VALUE)
                    .setMessage("One of the selected sneaker is not available")
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void getSneakerPrice(SneakerIdsRequest request, StreamObserver<OrderTotalPriceResponse> responseObserver) {
        Double totalPrice = sneakerService.getSneakerPrices(request.getSneakerIdList());
        OrderTotalPriceResponse totalPriceResponse = OrderTotalPriceResponse.newBuilder()
                .setPrice(totalPrice)
                .build();
        responseObserver.onNext(totalPriceResponse);
        responseObserver.onCompleted();
    }
}
