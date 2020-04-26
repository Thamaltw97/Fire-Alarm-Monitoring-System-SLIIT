using FireAlarmMonitoringSystem.Web.Data.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace FireAlarmMonitoringSystem.Web.Data.Persistence
{
    public class UserPersistenceService : IUserPersistenceService
    {
        private readonly AppDbContext _context;

        public UserPersistenceService() { }

        public UserPersistenceService(AppDbContext context)
        {
            _context = context;
        }

        public async Task<UserModel> SignIn(UserModel user)
        {
            UserModel actualUser = await _context.Users.FirstOrDefaultAsync(
                dbUserObj => dbUserObj.userEmail.Equals(user.userEmail) && dbUserObj.userPassword.Equals(user.userPassword)
            );

            if (actualUser == null)
            {
                return null;
            }
            else
            {
                //actualUser.userModel = await _context.Users.FirstOrDefaultAsync(userModel => userModel.userId == actualUser.userId);
                return actualUser;
            }
            
        }
    }
}
