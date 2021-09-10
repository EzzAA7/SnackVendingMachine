package com.company;

public class VMRunnder {
    private VendingMachine vm;

    public VMRunnder(VendingMachine vm) {
        this.vm = vm;
    }

    public VendingMachine getVm() {
        return vm;
    }

    public void setVm(VendingMachine vm) {
        this.vm = vm;
    }
}
