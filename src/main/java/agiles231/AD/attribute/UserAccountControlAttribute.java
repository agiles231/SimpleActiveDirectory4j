package agiles231.AD.attribute;

public class UserAccountControlAttribute {

	boolean script;
	boolean disabled;
	boolean homeDirRequired;
	boolean lockout;
	boolean passwordNotRequired;
	boolean passwordCantChange;
	boolean encyptedTextPasswordAllowed;
	boolean tempDuplicateAccount;
	boolean normalAccount;
	boolean interdomainTrustAccount;
	boolean workstationTrustAccount;
	boolean serverTrustAccount;
	boolean dontExpirePassword;
	boolean mnsLogonAccount;
	boolean smartcardRequired;
	boolean trustedForDelegation;
	boolean notDelegated;
	boolean useDesKeyOnly;
	boolean dontRequirePreauth;
	boolean passwordExpired;
	boolean trustedToAuthForDelegation;
	boolean partialSecretsAccount;

	private final static int[] flagValues = { 0x0001, 0x0002, 0x0008, 0x0010, 0x0020, 0x0040, 0x0080, 0x0100, 0x0200,
			0x0800, 0x1000, 0x2000, 0x10000, 0x20000, 0x40000, 0x80000, 0x100000, 0x200000, 0x400000, 0x800000,
			0x1000000, 0x04000000 };

	public UserAccountControlAttribute(int flags) {
		setFlags(flags);
	}

	public void setFlags(int flags) {
		int copy = flags;
		for (int i : flagValues) {
			copy -= copy & i;
		}
		if (copy != 0) {
			throw new IllegalArgumentException("Flags for this field must match values as defined at \n"
					+ "https://support.microsoft.com/en-us/help/305144/how-to-use-useraccountcontrol-to-manipulate-user-account-properties");
		}
		script = (flags & flagValues[0]) == flagValues[0];
		disabled = (flags & flagValues[1]) == flagValues[1];
		homeDirRequired = (flags & flagValues[2]) == flagValues[2];
		lockout = (flags & flagValues[3]) == flagValues[3];
		passwordNotRequired = (flags & flagValues[4]) == flagValues[4];
		passwordCantChange = (flags & flagValues[5]) == flagValues[5];
		encyptedTextPasswordAllowed = (flags & flagValues[6]) == flagValues[6];
		tempDuplicateAccount = (flags & flagValues[7]) == flagValues[7];
		normalAccount = (flags & flagValues[8]) == flagValues[8];
		interdomainTrustAccount = (flags & flagValues[9]) == flagValues[9];
		workstationTrustAccount = (flags & flagValues[10]) == flagValues[10];
		serverTrustAccount = (flags & flagValues[11]) == flagValues[11];
		dontExpirePassword = (flags & flagValues[12]) == flagValues[12];
		mnsLogonAccount = (flags & flagValues[13]) == flagValues[13];
		smartcardRequired = (flags & flagValues[14]) == flagValues[14];
		trustedForDelegation = (flags & flagValues[15]) == flagValues[15];
		notDelegated = (flags & flagValues[16]) == flagValues[16];
		useDesKeyOnly = (flags & flagValues[17]) == flagValues[17];
		dontRequirePreauth = (flags & flagValues[18]) == flagValues[18];
		passwordExpired = (flags & flagValues[19]) == flagValues[19];
		trustedToAuthForDelegation = (flags & flagValues[20]) == flagValues[20];
		partialSecretsAccount = (flags & flagValues[21]) == flagValues[21];
	}

	public int getFlags() {
		int flags = 0;
		if (script) flags += flagValues[0];
		if (disabled) flags += flagValues[1];
		if (homeDirRequired) flags += flagValues[2];
		if (lockout) flags += flagValues[3];
		if (passwordNotRequired) flags += flagValues[4];
		if (passwordCantChange) flags += flagValues[5];
		if (encyptedTextPasswordAllowed) flags += flagValues[6];
		if (tempDuplicateAccount) flags += flagValues[7];
		if (normalAccount) flags += flagValues[8];
		if (interdomainTrustAccount) flags += flagValues[9];
		if (workstationTrustAccount) flags += flagValues[10];
		if (serverTrustAccount) flags += flagValues[11];
		if (dontExpirePassword) flags += flagValues[12];
		if (mnsLogonAccount) flags += flagValues[13];
		if (smartcardRequired) flags += flagValues[14];
		if (trustedForDelegation) flags += flagValues[15];
		if (notDelegated) flags += flagValues[16];
		if (useDesKeyOnly) flags += flagValues[17];
		if (dontRequirePreauth) flags += flagValues[18];
		if (passwordExpired) flags += flagValues[19];
		if (trustedToAuthForDelegation) flags += flagValues[20];
		if (partialSecretsAccount) flags += flagValues[21];
		return flags;
	}
	
	public String getID() {
		return "userAccountControl";
	}

	public boolean isScript() {
		return script;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public boolean isHomeDirRequired() {
		return homeDirRequired;
	}

	public boolean isLockout() {
		return lockout;
	}

	public boolean isPasswordNotRequired() {
		return passwordNotRequired;
	}

	public boolean isPasswordCantChange() {
		return passwordCantChange;
	}

	public boolean isEncyptedTextPasswordAllowed() {
		return encyptedTextPasswordAllowed;
	}

	public boolean isTempDuplicateAccount() {
		return tempDuplicateAccount;
	}

	public boolean isNormalAccount() {
		return normalAccount;
	}

	public boolean isInterdomainTrustAccount() {
		return interdomainTrustAccount;
	}

	public boolean isWorkstationTrustAccount() {
		return workstationTrustAccount;
	}

	public boolean isServerTrustAccount() {
		return serverTrustAccount;
	}

	public boolean isDontExpirePassword() {
		return dontExpirePassword;
	}

	public boolean isMnsLogonAccount() {
		return mnsLogonAccount;
	}

	public boolean isSmartcardRequired() {
		return smartcardRequired;
	}

	public boolean isTrustedForDelegation() {
		return trustedForDelegation;
	}

	public boolean isNotDelegated() {
		return notDelegated;
	}

	public boolean isUseDesKeyOnly() {
		return useDesKeyOnly;
	}

	public boolean isDontRequirePreauth() {
		return dontRequirePreauth;
	}

	public boolean isPasswordExpired() {
		return passwordExpired;
	}

	public boolean isTrustedToAuthForDelegation() {
		return trustedToAuthForDelegation;
	}

	public boolean isPartialSecretsAccount() {
		return partialSecretsAccount;
	}

	public void setScript(boolean script) {
		this.script = script;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setHomeDirRequired(boolean homeDirRequired) {
		this.homeDirRequired = homeDirRequired;
	}

	public void setLockout(boolean lockout) {
		this.lockout = lockout;
	}

	public void setPasswordNotRequired(boolean passwordNotRequired) {
		this.passwordNotRequired = passwordNotRequired;
	}

	public void setPasswordCantChange(boolean passwordCantChange) {
		this.passwordCantChange = passwordCantChange;
	}

	public void setEncyptedTextPasswordAllowed(boolean encyptedTextPasswordAllowed) {
		this.encyptedTextPasswordAllowed = encyptedTextPasswordAllowed;
	}

	public void setTempDuplicateAccount(boolean tempDuplicateAccount) {
		this.tempDuplicateAccount = tempDuplicateAccount;
	}

	public void setNormalAccount(boolean normalAccount) {
		this.normalAccount = normalAccount;
	}

	public void setInterdomainTrustAccount(boolean interdomainTrustAccount) {
		this.interdomainTrustAccount = interdomainTrustAccount;
	}

	public void setWorkstationTrustAccount(boolean workstationTrustAccount) {
		this.workstationTrustAccount = workstationTrustAccount;
	}

	public void setServerTrustAccount(boolean serverTrustAccount) {
		this.serverTrustAccount = serverTrustAccount;
	}

	public void setDontExpirePassword(boolean dontExpirePassword) {
		this.dontExpirePassword = dontExpirePassword;
	}

	public void setMnsLogonAccount(boolean mnsLogonAccount) {
		this.mnsLogonAccount = mnsLogonAccount;
	}

	public void setSmartcardRequired(boolean smartcardRequired) {
		this.smartcardRequired = smartcardRequired;
	}

	public void setTrustedForDelegation(boolean trustedForDelegation) {
		this.trustedForDelegation = trustedForDelegation;
	}

	public void setNotDelegated(boolean notDelegated) {
		this.notDelegated = notDelegated;
	}

	public void setUseDesKeyOnly(boolean useDesKeyOnly) {
		this.useDesKeyOnly = useDesKeyOnly;
	}

	public void setDontRequirePreauth(boolean dontRequirePreauth) {
		this.dontRequirePreauth = dontRequirePreauth;
	}

	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public void setTrustedToAuthForDelegation(boolean trustedToAuthForDelegation) {
		this.trustedToAuthForDelegation = trustedToAuthForDelegation;
	}

	public void setPartialSecretsAccount(boolean partialSecretsAccount) {
		this.partialSecretsAccount = partialSecretsAccount;
	}

	@Override
	public String toString() {
		return "UserAccountControlAttribute [script=" + script + ", disabled=" + disabled + ", homeDirRequired="
				+ homeDirRequired + ", lockout=" + lockout + ", passwordNotRequired=" + passwordNotRequired
				+ ", passwordCantChange=" + passwordCantChange + ", encyptedTextPasswordAllowed="
				+ encyptedTextPasswordAllowed + ", tempDuplicateAccount=" + tempDuplicateAccount + ", normalAccount="
				+ normalAccount + ", interdomainTrustAccount=" + interdomainTrustAccount + ", workstationTrustAccount="
				+ workstationTrustAccount + ", serverTrustAccount=" + serverTrustAccount + ", dontExpirePassword="
				+ dontExpirePassword + ", mnsLogonAccount=" + mnsLogonAccount + ", smartcardRequired="
				+ smartcardRequired + ", trustedForDelegation=" + trustedForDelegation + ", notDelegated="
				+ notDelegated + ", useDesKeyOnly=" + useDesKeyOnly + ", dontRequirePreauth=" + dontRequirePreauth
				+ ", passwordExpired=" + passwordExpired + ", trustedToAuthForDelegation=" + trustedToAuthForDelegation
				+ ", partialSecretsAccount=" + partialSecretsAccount + "]";
	}
}
