# hclHealthCareHackathon
API endpoints:

/login
  Request
    {
      username,
      password
    }
  Response
    {
      status
      id
    }

/createShift
  Request
    {
      date,
      type,
      capacity
    }
  Response
    {
      status,
      id
    }

/getShiftByDate/:date
  Response
    {
      List<ShiftInstance>
    }


/addStaff
  Request
    {
      name,
      role,
      shift_preference,
      email,
      contact
    }
  Response 
    {
      status,
      id
    }

/removeStaff/:id
  Response 
    {
      is_deleted: true/false
    }

/fetchAllStaffs
  Response
    {
      List<Staffs>
    }