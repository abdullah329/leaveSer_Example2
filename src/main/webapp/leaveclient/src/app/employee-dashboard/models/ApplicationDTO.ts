import {NgbDate} from "@ng-bootstrap/ng-bootstrap";

export class ApplicationDTO{
    id: number;
    startDateAg: string;
    startDateAh: NgbDate;
    duration: number;
    type: number;
    typeName: string;
    status: number;
    statusType: string;
}
