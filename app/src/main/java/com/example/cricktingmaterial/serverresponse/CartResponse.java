package com.example.spareworld.serverreponse;

public class CartResponse {
    private String status;

        public CartResponse(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
}
