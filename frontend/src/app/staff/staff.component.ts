import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

interface Staff {
  id: number;
  name: string;
  role: string;
  shiftPreference: string;
  email: string;
  contactNumber: string;
}

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent {

  staffs: Staff[] = [];
  staffForm: FormGroup;

  constructor(private modalService: NgbModal, private fb: FormBuilder) {
    this.staffForm = this.fb.group({
      name: ['', Validators.required],
      role: ['', Validators.required],
      shiftPreference: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contactNumber: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
    });
  }

  openAddModal(content: any) {
    this.staffForm.reset();
    this.modalService.open(content, { centered: true });
  }

  addStaff(modalRef: any) {
    if (this.staffForm.valid) {
      const id = this.staffs.length + 1;
      const newStaff: Staff = { id, ...this.staffForm.value };
      this.staffs.push(newStaff);
      modalRef.close();
    }
  }

  deleteStaff(index: number) {
    this.staffs.splice(index, 1);
  }

}
