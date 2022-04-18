package edu.gatech.GroceryExpress.gateways;

public class GatewayFactoryImplementation implements GatewayFactory {
    @Override
    public GatewayRepository createGatewayRepository() {
        return new InMemoryGatewayRepository();
    }
}
