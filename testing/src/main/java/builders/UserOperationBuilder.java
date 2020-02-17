package builders;

import lombok.NoArgsConstructor;
import model.UserOperation;

@NoArgsConstructor
public class UserOperationBuilder {

    UserOperation userOperation = new UserOperation();

    public UserOperationBuilder withUsername(String username){
        userOperation.setUsername(username);
        return this;
    }

    public UserOperationBuilder withPassword(String password){
        userOperation.setPassword(password);
        return this;
    }

    public UserOperationBuilder withOperation(String operation){
        userOperation.setOperation(operation);
        return this;
    }

    public UserOperation build(){
        UserOperation returnVal = this.userOperation;
        this.userOperation = new UserOperation();
        return returnVal;
    }

}
