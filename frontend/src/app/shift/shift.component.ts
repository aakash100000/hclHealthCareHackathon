import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

interface Shift {
  id: number;
  date: string;
  type: string;
  capacity: number;
  assignedStaff: string[];
}

@Component({
  selector: 'app-shifts',
  templateUrl: './shift.component.html',
})
export class ShiftComponent {
  shifts: Shift[] = [];
  shiftForm: FormGroup;
  today = new Date();

  allStaffs: string[] = ['Alice', 'Bob', 'Charlie', 'Diana', 'Eve', 'Frank'];
  filteredStaffs: string[] = [];
  selectedStaffs: Set<string> = new Set();
  currentShiftIndex: number | null = null;

  constructor(private fb: FormBuilder, private modalService: NgbModal) {
    this.shiftForm = this.fb.group({
      date: ['', Validators.required],
      type: ['', Validators.required],
      capacity: ['', [Validators.required, Validators.min(1)]],
    });
  }

  openAddModal(content: any) {
    this.shiftForm.reset();
    this.modalService.open(content, { centered: true });
  }

  addShift(modalRef: any) {
    if (this.shiftForm.valid) {
      const id = this.shifts.length + 1;
      const newShift: Shift = {
        id,
        ...this.shiftForm.value,
        date: this.shiftForm.value.date,
        assignedStaff: []
      };
      this.shifts.push(newShift);
      modalRef.close();
    }
  }

  openAssignModal(modal: any, index: number) {
    this.currentShiftIndex = index;
    const currentShift = this.shifts[index];
    this.selectedStaffs = new Set(currentShift.assignedStaff);

    const assignedStaff = this.shifts
      .filter((s, i) => i !== index && s.date === currentShift.date)
      .flatMap(s => s.assignedStaff);
      
    this.filteredStaffs = this.allStaffs.filter(staff => !assignedStaff.includes(staff));
    this.modalService.open(modal, { centered: true });
  }

  toggleStaffSelection(staff: string) {
    if (this.selectedStaffs.has(staff)) {
      this.selectedStaffs.delete(staff);
    } else if (this.selectedStaffs.size < this.currentShiftCapacity) {
      this.selectedStaffs.add(staff);
    }
  }

  assignStaffs(modal: any) {
    if (
      this.currentShiftIndex !== null &&
      this.selectedStaffs.size <= this.shifts[this.currentShiftIndex].capacity
    ) {
      this.shifts[this.currentShiftIndex].assignedStaff = Array.from(this.selectedStaffs);
      modal.close();
    }
  }

  get selectedCount(): number {
    return this.selectedStaffs.size;
  }

  get currentShiftCapacity(): number {
    return this.currentShiftIndex !== null
      ? this.shifts[this.currentShiftIndex].capacity
      : 0;
  }

  isCheckboxDisabled(staff: string): boolean {
    return (
      !this.selectedStaffs.has(staff) &&
      this.selectedStaffs.size >= this.currentShiftCapacity
    );
  }
}
