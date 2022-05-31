import { DefaultModel } from '../shared/default-model';

export class LoyaltyProgramModel extends DefaultModel {
  clientPointsForReservation: number | undefined;
    pointsToRegular: number | undefined;
    pointsToSilver: number | undefined;
    pointsToGold: number | undefined;
    percentRegular: number | undefined;
    percentSilver: number | undefined;
    percentGold: number | undefined;

  public constructor(init?: Partial<LoyaltyProgramModel>) {
    super();
    Object.assign(this, init);
  }
}