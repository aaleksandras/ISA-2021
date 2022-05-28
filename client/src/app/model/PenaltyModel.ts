export class Penalty {
    reason: string;
    username: string;
    userComment ?: string;
    constructor() {
      this.username = '';
      this.reason = '';
      this.userComment = '';
    }
  }