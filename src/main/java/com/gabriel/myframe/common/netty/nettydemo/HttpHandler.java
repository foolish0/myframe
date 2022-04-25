package com.gabriel.myframe.common.netty.nettydemo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // ctx.write()并不会立即完成，而是缓存起来，当调用ctx.flush()时才会被发送
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            if (uri.startsWith("/hello")) {
                service(ctx, request, "hello, Gabriel!");
            } else {
                service(ctx, request, "who are you?");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void service(ChannelHandlerContext ctx, FullHttpRequest request, String body) throws RuntimeException{
        if (Objects.isNull(request)) {
            throw new RuntimeException("请求体为空！");
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        try {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
                    , Unpooled.wrappedBuffer(body.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
        } catch (Exception e) {
            logger.error("处理业务接口出错：", e);
        } finally {
            if (!HttpUtil.isKeepAlive(request)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }
}
