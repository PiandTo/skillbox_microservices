create SCHEMA user_schema;
create TABLE user_schema.user (
    id UUID PRIMARY KEY,
    name VARCHAR(60),
    surname VARCHAR(60),
    patronymic VARCHAR(60),
    gender VARCHAR(60),
    email VARCHAR(60),
    phone_number VARCHAR(60),
    login VARCHAR(60),
    avatar VARCHAR(60),
    descriptiion VARCHAR(120),
    address_id UUID,
    CONSTRAINT user_address_fk FOREIGN KEY (address_id) REFERENCES user_schema.address(address_id)
);

create TABLE user_schema.subscription (
    subscription_id UUID PRIMARY KEY,
    name VARCHAR(60)
);

create TABLE user_schema.user_subscription (
    user_id UUID,
    subscription_id UUID,
    CONSTRAINT subscription_fk FOREIGN KEY(subscription_id) REFERENCES user_schema.subscription(subscription_id),
    CONSTRAINT user_fk FOREIGN KEY(user_id) REFERENCES user_schema.user(id)
);
