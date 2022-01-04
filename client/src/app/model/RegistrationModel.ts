import { DefaultModel } from '../shared/default-model';

export class RegistrationModel extends DefaultModel {
  firstName: string | undefined;
  lastName: string | undefined;
  email: string | undefined;
  password: string | undefined;
  address: string | undefined;
  city: string | undefined;
  country: string | undefined;
  phoneNumber: string | undefined;
  typeOfUser: string | undefined;

  public constructor(init?: Partial<RegistrationModel>) {
    super();
    Object.assign(this, init);
  }
}
